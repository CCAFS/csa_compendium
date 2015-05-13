package org.cgiar.ccafs.csa.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.cgiar.ccafs.csa.domain.*;
import org.cgiar.ccafs.csa.repository.*;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DataExtractionService {

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


    // Status variables that indicate the stage of the file parsing process

    List<String> sections = Collections.unmodifiableList(Arrays.asList(
            "Article information",
            "Context",
            "BLOCK"
    ));

    public void extractArticleInformation(Iterable<Row> sheet) {

        ExperimentArticle article = new ExperimentArticle();
        Location location = new Location();
        Treatment treatment = new Treatment();

        loop:
        for (Row row : sheet) {
            Cell cell = row.getCell(0);
            if (cell == null || cell.getCellType() != Cell.CELL_TYPE_STRING) continue;

            String info = cell.getStringCellValue();
            cell = row.getCell(2);
            if (cell == null) continue;

            switch (info) {
                case "ID":
                    article.setCode(getCellStringValue(cell));
                    break;
                case "Theme":
                    article.setPracticeTheme(practiceThemeRepository.findByCode(getCellStringValue(cell)));
                    break;
                case "Title":
                    article.setTitle(cell.getStringCellValue());
                case "Author":
                    article.addAuthor(cell.getStringCellValue());
                case "Year":
                    if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        article.setPublicationDate(new LocalDate((int) Math.round(cell.getNumericCellValue()), 1, 1));
                    }
                    break;
                case "Country":
                    location.setCountry(countryRepository.findByName(cell.getStringCellValue()));
                    break;
                case "City":
                    location.setPlace(cell.getStringCellValue());
                    break;
                case "Latitude":
                    // If no Latitude, try google maps
                    location.setLatitude(cell.getStringCellValue());
                    break;
                case "Longitude":
                    location.setLongitude(cell.getStringCellValue());
                case "Altitude":
                    if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        location.setAltitude(Double.valueOf(cell.getNumericCellValue()).floatValue());
                        break;
                    }
                case "Experimental unit":
                    treatment.addProductionSystem(productionSystemRepository.findByCode(getCellStringValue(cell)));
                    break;
                case "Farming system FAO":
                    article.setFarmingSystem(farmingSystemRepository.findByCode(getCellStringValue(cell)));
                    break;
                default:
                    if (row.getCell(1) != null && "PracticeCode".equals(getCellStringValue(row.getCell(1)))) {
                        treatment.setPractice(practiceRepository.findByCode(getCellStringValue(cell)));
                        break loop;
                    }
            }
        }

        locationRepository.save(location);

        article.setLocation(location);
        article.addTreatment(treatment);

        experimentArticleRepository.save(article);
        treatmentRepository.save(treatment);
    }

    private String getCellStringValue(Cell cell) {
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            return String.valueOf((int) cell.getNumericCellValue());
        } else {
            return cell.getStringCellValue();
        }
    }

    public void extractPracticesInformation(XSSFSheet sheet) {

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
            newPractice.setDescription(practiceDescription);
            newPractice.setPracticeLevel(level);
            practiceRepository.save(newPractice);
        }
    }


    public void extractIndicatorsInformation(XSSFSheet sheet) {
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
}
