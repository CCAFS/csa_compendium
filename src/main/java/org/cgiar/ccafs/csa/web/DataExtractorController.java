package org.cgiar.ccafs.csa.web;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.cgiar.ccafs.csa.domain.*;
import org.cgiar.ccafs.csa.repository.*;
import org.joda.time.LocalDate;
import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.faces.bean.ManagedBean;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@ManagedBean
@Controller
public class DataExtractorController {

    private final Logger log = LoggerFactory.getLogger(DataExtractorController.class);

    @Autowired
    private ExperimentArticleRepository experimentArticleRepository;

    @Autowired
    private TreatmentRepository treatmentRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private FarmingSystemRepository farmingSystemRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private ProductionSystemRepository productionSystemRepository;

    @Autowired
    private PracticeThemeRepository practiceThemeRepository;

    @Autowired
    private PracticeLevelRepository practiceLevelRepository;

    @Autowired
    private PracticeRepository practiceRepository;

    @Autowired
    private IndicatorRepository indicatorRepository;

    private String result = "";

    @Value("${csa.tmp-dir}")
    private String tempStorageLocation;

    @RequestMapping(value = "/data_extractor", method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                Workbook book = WorkbookFactory.create(file.getInputStream());
                extractArticleInformation(book.getSheetAt(0));
            } catch (Exception e) {
                log.error("Failed to upload file", e.getMessage());
            }
        } else {
            log.error("Failed to upload file, was empty.");
        }
        return "redirect:/admin/";
    }

    public void uploadListener(FileUploadEvent event) {
        try {
            UploadedFile file = event.getUploadedFile();

            if (file.getFileExtension().equals("zip")) {
                File tempFile = new File(tempStorageLocation, file.getName());
                byte[] bytes = file.getData();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(tempFile));
                stream.write(bytes);
                stream.close();

                ZipFile zipFile = new ZipFile(tempFile);
                Workbook workbook;
                ZipEntry zipEntry;

                for (Enumeration<? extends ZipEntry> files = zipFile.entries(); files.hasMoreElements(); ) {
                    zipEntry = files.nextElement();

                    try {
                        workbook = WorkbookFactory.create(zipFile.getInputStream(zipEntry));
                        Sheet sheet = workbook.getSheetAt(0);
                        extractArticleInformation(sheet);
                    } catch (InvalidFormatException i) {
                        log.info("Not a supported format: " + zipEntry.getName());
                    } catch (Exception e) {
                        log.info("Fail to load: " + zipEntry.getName(), e);
                    }
                }

                zipFile.close();
                tempFile.delete();

            } else {
                XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
                XSSFSheet sheet = workbook.getSheetAt(0);

                if (file.getName().equals("Practices.xlsx")) {
                    extractPracticesInformation(sheet);
                } else if (file.getName().equals("Indicators.xlsx")) {
                    extractIndicatorsInformation(sheet);
                } else if (file.getName().equals("Compendium.xlsx")) {
                    extractCompendium(sheet);
                }
                workbook.close();
            }
            result += file.getName() + "  ";
        } catch (IOException e) {
            log.error("Can't process the file.", e);
            result = "Things didn't went well. Check logs.";
        }
    }

    private void extractArticleInformation(Iterable<Row> sheet) {

        ExperimentArticle article = new ExperimentArticle();
        Location location = new Location();
        Treatment treatment = new Treatment();

        loop: for (Row row : sheet) {
            Cell cell = row.getCell(0);
            if (cell == null || cell.getCellType() != Cell.CELL_TYPE_STRING) continue;

            String info = cell.getStringCellValue();
            cell = row.getCell(2);
            if (cell == null) continue;

            switch (info) {
                case "ID" :
                    article.setCode(getCellStringValue(cell)); break;
                case "Theme" :
                    article.setPracticeTheme(practiceThemeRepository.findByCode(getCellStringValue(cell))); break;
                case "Title" :
                    article.setTitle(cell.getStringCellValue());
                case "Author" :
                    article.addAuthor(cell.getStringCellValue());
                case "Year" :
                    if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        article.setPublicationDate(new LocalDate((int) Math.round(cell.getNumericCellValue()), 1, 1));
                    }
                    break;
                case "Country" :
                    location.setCountry(countryRepository.findByName(cell.getStringCellValue())); break;
                case "City" :
                    location.setPlace(cell.getStringCellValue()); break;
                case "Latitude" :
                    // If no Latitude, try google maps
                    location.setLatitude(cell.getStringCellValue()); break;
                case "Longitude" :
                    location.setLongitude(cell.getStringCellValue());
                case "Altitude" :
                    if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        location.setAltitude(Double.valueOf(cell.getNumericCellValue()).floatValue()); break;
                    }
                case "Experimental unit" :
                    treatment.addProductionSystem(productionSystemRepository.findByCode(getCellStringValue(cell))); break;
                case "Farming system FAO" :
                    article.setFarmingSystem(farmingSystemRepository.findByCode(getCellStringValue(cell))); break;
                default:
                    if (row.getCell(1) != null && "PracticeCode".equals(getCellStringValue(row.getCell(1)))) {
                        treatment.setPractice(practiceRepository.findByCode(getCellStringValue(cell)));
                        break loop;
                    }
            }
        }

        locationRepository.save(location);
        treatmentRepository.save(treatment);

        article.setLocation(location);
        article.addTreatment(treatment);

        experimentArticleRepository.save(article);
    }

    private String getCellStringValue(Cell cell) {
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            return String.valueOf((int) cell.getNumericCellValue());
        } else {
            return cell.getStringCellValue();
        }
    }

    private void extractPracticesInformation(XSSFSheet sheet) {

        for (Row row : sheet) {
            // Read the code
            Cell cell = row.getCell(0);
            String practiceCode = cell.getStringCellValue();

            cell = row.getCell(1);
            String practiceTheme = cell.getStringCellValue();

            cell = row.getCell(2);
            String practiceLevel = cell.getStringCellValue();

            cell = row.getCell(3);
            String practiceName = cell.getStringCellValue();

            cell = row.getCell(4);
            String practiceDescription = cell.getStringCellValue();

            PracticeTheme theme = practiceThemeRepository.findByName(practiceTheme);

            if (theme == null) {
                theme = new PracticeTheme();
                theme.setName(practiceTheme);
                theme = practiceThemeRepository.save(theme);
                theme.setCode(String.valueOf(theme.getId()));
                theme = practiceThemeRepository.save(theme);
            }

            PracticeLevel level = practiceLevelRepository.findByName(practiceLevel);

            if (level == null) {
                level = new PracticeLevel();
                level.setName(practiceLevel);
                level.setTheme(theme);
                level = practiceLevelRepository.save(level);
                level.setCode(String.valueOf(level.getId()));
                practiceLevelRepository.save(level);
            }

            Practice newPractice = new Practice();
            newPractice.setCode(practiceCode);
            newPractice.setName(practiceName);

            if (practiceDescription.length() > 255) {
                newPractice.setDocumentation(practiceDescription);
            } else {
                newPractice.setDescription(practiceDescription);
            }

            newPractice.setPracticeLevel(level);
            practiceRepository.save(newPractice);
        }
    }

    private void extractIndicatorsInformation(XSSFSheet sheet) {
        for (Row row : sheet) {
            Cell cell = row.getCell(0);
            String indicatorCode = String.valueOf(cell.getNumericCellValue());

            cell = row.getCell(1);
            String indicatorPillar = cell.getStringCellValue();

            cell = row.getCell(2);
            String indicatorCategory = cell.getStringCellValue();

            cell = row.getCell(3);
            String indicatorName = cell.getStringCellValue();

            Indicator indicator = new Indicator();

            indicator.setCode(indicatorCode);
            indicator.setCategory(indicatorCategory);
            indicator.setName(indicatorName);

            indicator.addPillar(new IndicatorPillar(Pillar.getByName(indicatorPillar), 1.0f));

            indicatorRepository.save(indicator);
        }
    }

    private void extractCompendium(XSSFSheet sheet) {

    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
