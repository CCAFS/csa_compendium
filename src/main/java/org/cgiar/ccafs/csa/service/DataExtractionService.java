package org.cgiar.ccafs.csa.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.cgiar.ccafs.csa.domain.*;
import org.cgiar.ccafs.csa.repository.*;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class DataExtractionService {

    List<String> sections = Collections.unmodifiableList(Arrays.asList(
            "Article information",
            "Context",
            "BLOCK"
    ));
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
    @Autowired
    private SubIndicatorRepository subIndicatorRepository;
    @Autowired
    private IndicatorPillarRepository indicatorPillarRepository;


    public void extractArticleInformation(Iterable<Row> sheet) {

        ExperimentArticle article = new ExperimentArticle();
        Location location = new Location();
        Treatment treatment = new Treatment();

        loop:
        for (Row row : sheet) {
            Cell cell = row.getCell(0);
            if (cell == null || cell.getCellType() != Cell.CELL_TYPE_STRING) continue;

            String info = getCellStringValue(cell);
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
        if (cell == null) return null;
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            return String.valueOf(Math.round(cell.getNumericCellValue()));
        } else {
            return cell.getStringCellValue();
        }
    }

    public void extractPracticesInformation(XSSFSheet sheet) {

        for (Row row : sheet) {
            // Read the code
            Cell cell = row.getCell(0);
            String practiceCode = getCellStringValue(cell);

            cell = row.getCell(1);
            String practiceTheme = getCellStringValue(cell);

            cell = row.getCell(2);
            String practiceLevel = getCellStringValue(cell);

            cell = row.getCell(3);
            String practiceName = getCellStringValue(cell);

            cell = row.getCell(4);
            String practiceDescription = getCellStringValue(cell);

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
            String subIndicatorCode = getCellStringValue(cell);

            cell = row.getCell(1);
            String indicatorPillar = getCellStringValue(cell);

            cell = row.getCell(2);
            String indicatorName = getCellStringValue(cell);

            cell = row.getCell(3);
            String subIndicatorName = getCellStringValue(cell);

            cell = row.getCell(4);
            String subIndicatorDescription = getCellStringValue(cell);

            Indicator indicator = indicatorRepository.findByName(indicatorName);

            if (indicator == null) {
                indicator = new Indicator();
                indicator.setName(indicatorName);
                IndicatorPillar pillar = new IndicatorPillar(Pillar.getByName(indicatorPillar), 1.0f);
                indicator.addPillar(pillar);
                indicatorRepository.save(indicator);
                indicatorPillarRepository.save(pillar);
            }

            SubIndicator subIndicator = new SubIndicator();

            subIndicator.setCode(subIndicatorCode);
            subIndicator.setName(subIndicatorName);
            if (subIndicatorDescription != null && !subIndicatorDescription.isEmpty()) {
                subIndicator.setDescription(subIndicatorDescription);
            }

            indicator.addSubIndicator(subIndicator);
            subIndicatorRepository.save(subIndicator);
        }
    }
}
