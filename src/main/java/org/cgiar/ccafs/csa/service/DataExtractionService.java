package org.cgiar.ccafs.csa.service;

import com.google.common.base.Strings;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.cgiar.ccafs.csa.domain.*;
import org.cgiar.ccafs.csa.repository.*;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class DataExtractionService {

    private final Logger log = LoggerFactory.getLogger(DataExtractionService.class);

    private List<String> SECTIONS = Collections.unmodifiableList(Arrays.asList(
            "Controls",
            "Treatments",
            "Outcomes",
            "Barriers"
    ));

    private List<String> badValues = Collections.unmodifiableList(Arrays.asList(
            "c1", "t1", "t2", "t3", "t4", "t5", "5/1988-10/188"
    ));

    private ContextVariable documentType;
    private ContextVariable typeOfStudy;

    private class DataExtractionState {
        Map<String, Treatment> treatments = new HashMap<>();

        String section = "Article";
        ExperimentContext context;
        int contextNumber;
        InitialCondition condition = new InitialCondition();
        Treatment treatment = new Treatment();
        Treatment control;
        ProductionSystem productionSystem;
        SubIndicator subIndicator;
        TreatmentOutcome outcome;
        TreatmentBarrier barrier;
        String measureUnitSymbol;
    }

    @PostConstruct
    private void init() {
        documentType = contextVariableRepository.findOne(1);
        typeOfStudy = contextVariableRepository.findOne(2);
    }

    @Autowired
    private ExperimentArticleRepository experimentArticleRepository;
    @Autowired
    private ExperimentContextRepository experimentContextRepository;
    @Autowired
    private ContextVariableRepository contextVariableRepository;
    @Autowired
    private ContextValueRepository contextValueRepository;
    @Autowired
    private ConditionRepository conditionRepository;
    @Autowired
    private InitialConditionRepository initialConditionRepository;
    @Autowired
    private MeasureUnitRepository measureUnitRepository;
    @Autowired
    private BarrierRepository barrierRepository;
    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private TreatmentRepository treatmentRepository;
    @Autowired
    private TreatmentOutcomeRepository treatmentOutcomeRepository;
    @Autowired
    private TreatmentBarrierRepository treatmentBarrierRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private FarmingSystemRepository farmingSystemRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private ProductionSystemRepository productionSystemRepository;
    @Autowired
    private ProductionSystemCategoryRepository productionSystemCategoryRepository;
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

    @Transactional
    public void extractArticleInformation(Iterable<Row> sheet) {

        ExperimentArticle article = new ExperimentArticle();
        DataExtractionState current = new DataExtractionState();

        // Main loop, checks every row
        for (Row row : sheet) {
            // First cell of the row may have three meanings: Section change, sub-section change and
            // control/treatment identification
            Cell cell = row.getCell(0);
            // Skips rows that are empty or start with numbers
            if (cell == null || cell.getCellType() != Cell.CELL_TYPE_STRING) continue;
            String value = getCellStringValue(cell);
            String subValue = getCellStringValue(row.getCell(1));
            cell = row.getCell(2);

            // Section change. Initial section is always Article
            if (value.startsWith("Context")) {
                current.section = "Context";
                current.contextNumber++;
                current.context = new ExperimentContext();
                current.context.setLocation(new Location());
                article.addContext(current.context);
            } else if (SECTIONS.contains(value)) {
                current.section = value;
            } else {
                // Skips rows with no value for the variable
                if (!current.section.equals("Outcomes") && (cell == null || getCellStringValue(cell).isEmpty()))
                    continue;

                String treatmentKey = value + current.contextNumber;

                switch (current.section) {
                    case "Article":
                        extractArticleDataField(value, cell, article);
                        break;
                    case "Context":
                        extractContextDataField(value, subValue, cell, current);
                        break;
                    case "Controls":
                        addTreatmentsToContext(treatmentKey, subValue, cell, true, current);
                        break;
                    case "Treatments":
                        addTreatmentsToContext(treatmentKey, subValue, cell, false, current);
                        break;
                    case "Outcomes":
                        extractOutcomeInformation(treatmentKey, subValue, cell, current);
                        break;
                    case "Barriers":
                        extractBarrierInformation(treatmentKey, subValue, cell, current);
                }
            }
        }

        for (ExperimentContext context : article.getContexts()) {
            locationRepository.save(context.getLocation());

            for (InitialCondition condition : context.getInitialConditions()) {
                initialConditionRepository.save(condition);
            }

            for (Treatment treatment : context.getTreatments()) {
                for (TreatmentOutcome treatmentOutcome : treatment.getOutcomes()) {
                    treatmentOutcomeRepository.save(treatmentOutcome);
                }
                for (TreatmentBarrier treatmentBarrier : treatment.getBarriers()) {
                    treatmentBarrierRepository.save(treatmentBarrier);
                }
                if (treatment.getControl() != null && treatment.getControl().getId() == null) {
                    treatmentRepository.save(treatment.getControl());
                }
                treatmentRepository.save(treatment);
            }

            experimentContextRepository.save(context);
        }

        experimentArticleRepository.save(article);
    }

    private void extractArticleDataField(String variable, Cell valueCell, ExperimentArticle article) {
        switch (variable) {
            case "ID":
                article.setCode(getCellStringValue(valueCell));
                break;
            case "Theme":
                article.setTheme(practiceThemeRepository.findByCode(getCellStringValue(valueCell)));
                break;
            case "Document type":
                article.setContextVariable(contextValueRepository
                        .findByContextVariableAndCode(documentType, getCellStringValue(valueCell)));
                break;
            case "Type of study":
                article.setContextVariable(contextValueRepository
                        .findByContextVariableAndCode(typeOfStudy, getCellStringValue(valueCell)));
                break;
            case "Language":
                article.setLanguage(languageRepository.findByCode(getCellStringValue(valueCell)));
                break;
            case "Title":
                article.setTitle(valueCell.getStringCellValue().trim());
                break;
            case "Author":
                article.addAuthor(valueCell.getStringCellValue().trim());
                break;
            case "Year":
                try {
                    article.setPublicationDate(new LocalDate(Integer.valueOf(getCellStringValue(valueCell)), 1, 1));
                } catch (NumberFormatException n) {
                    log.warn("Year issue: " + n.getMessage());
                }
                break;
            case "Contact":
                article.addContact(valueCell.getStringCellValue().trim());
        }
    }

    private void extractContextDataField(String variable, String parameterType, Cell valueCell,
                                         DataExtractionState current) {
        switch (variable) {
            case "Country":
                current.context.getLocation().setCountry(countryRepository.findByName(getCellStringValue(valueCell)));
                break;
            case "City":
                current.context.getLocation().setPlace(valueCell.getStringCellValue().trim());
                break;
            case "Latitude":
                // If no Latitude, try google maps
                current.context.getLocation().setLatitude(valueCell.getStringCellValue().trim());
                break;
            case "Longitude":
                current.context.getLocation().setLongitude(valueCell.getStringCellValue().trim());
                break;
            case "Altitude":
                String[] altitudeParts = getCellStringValue(valueCell).split("-");
                if (altitudeParts.length == 2) {
                    current.context.getLocation().setMinAltitude(getNumericValue(altitudeParts[0]));
                    current.context.getLocation().setMaxAltitude(getNumericValue(altitudeParts[1]));
                } else if (altitudeParts.length == 1) {
                    current.context.getLocation().setMaxAltitude(getNumericValue(altitudeParts[0]));
                }
                break;
            case "Experimental unit":
                Row row = valueCell.getRow();
                int i = 2; // Already on the value column
                Cell productionSystemCell;
                String productionSystemCode;
                while (((productionSystemCell = row.getCell(i++)) != null) &&
                        !(productionSystemCode = getCellStringValue(productionSystemCell).toLowerCase()).isEmpty()) {
                    ProductionSystem productionSystem = productionSystemRepository.findByCode(productionSystemCode);
                    if (productionSystem != null) {
                        current.context.addProductionSystem(productionSystem);
                    } else {
                        log.warn("Production System not found: " + productionSystemCode + " on column: " + i);
                    }
                }
                break;
            case "Farming system FAO":
                current.context.setFarmingSystem(farmingSystemRepository.findByCode(getCellStringValue(valueCell)));
                break;
            default:
                if (badValues.contains(variable)) {
                    throw new IllegalArgumentException("Incorrect condition: " + variable);
                }
                String value = getCellStringValue(valueCell);
                if (!(value.isEmpty() || value.startsWith("txt")) || value.startsWith("tab")) { //HACK Just to avoid descriptive values

                    Condition condition = conditionRepository.findByName(variable);

                    if (condition == null) { //HACK They all should be already in
                        condition = new Condition();
                        condition.setName(variable);
                        conditionRepository.save(condition);
                    }

                    if (!condition.equals(current.condition.getCondition())) {
                        current.condition = new InitialCondition();
                        current.condition.setCondition(condition);
                        current.context.addInitialCondition(current.condition);
                    }

                    if ("unit".equals(parameterType)) {
                        MeasureUnit unit = measureUnitRepository.findBySymbolAndName(value, "");
                        if (unit == null && !value.isEmpty()) { //HACK
                            unit = new MeasureUnit();
                            unit.setSymbol(value);
                            unit.setName("");
                            measureUnitRepository.save(unit);
                        }
                        current.condition.setMeasureUnit(unit);
                    } else {
                        if (valueCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                            current.condition.setValue(valueCell.getNumericCellValue());
                        } else {
                            current.condition.setState(value);
                        }
                    }
                }
        }
    }

    private void addTreatmentsToContext(String treatmentKey, String parameter, Cell valueCell,
                                        boolean isControl, DataExtractionState current) {
        if (!current.treatments.containsKey(treatmentKey)) {
            current.treatment = new Treatment();
            if (isControl) {
                current.control = current.treatment;
            } else {
                current.treatment.setControl(current.control);
            }
            current.treatment.setControlForTreatments(isControl);
            current.treatments.put(treatmentKey, current.treatment);
        }

        if (parameter.equals("PracticeCode") || parameter.equals("ControlCode")) {
            Row row = valueCell.getRow();
            int i = 2; // Already on the value column
            Cell practiceCell;
            String practiceCode;
            while (((practiceCell = row.getCell(i++)) != null) &&
                    !(practiceCode = getCellStringValue(practiceCell).toLowerCase()).isEmpty()) {
                Practice practice = practiceRepository.findByCode(practiceCode);
                if (practice != null) {
                    current.treatment.addPractice(practice);
                } else {
                    log.warn("Practice not found: " + practiceCode + " on column: " + i);
                }
            }
            current.context.addTreatment(current.treatment);
        } else {
            current.treatment.setInformation(getCellStringValue(valueCell));
        }
    }

    private void extractOutcomeInformation(String treatmentKey, String parameter, Cell valueCell, DataExtractionState current) {
        if (parameter.equals("Exp.UnitCode")) {
            current.productionSystem = productionSystemRepository.findByCode((getCellStringValue(valueCell)));
        } else if (parameter.equals("OutcomeCode")) {
            current.subIndicator = subIndicatorRepository.findByCode((getCellStringValue(valueCell)));
        } else if (current.treatments.containsKey(treatmentKey)) {
            current.treatment = current.treatments.get(treatmentKey);

            if (parameter.equals("period")) {
                current.outcome = new TreatmentOutcome();
                current.outcome.setProductionSystem(current.productionSystem);
                current.outcome.setSubIndicator(current.subIndicator);

                String[] periodArray = getCellStringValue(valueCell).split("-");
                if (periodArray.length == 2 && !periodArray[0].isEmpty() && !periodArray[0].isEmpty()) {
                    current.outcome.setStartDate(parseDate(periodArray[0]));
                    current.outcome.setEndDate(parseDate(periodArray[1]));
                } else if (periodArray.length == 1 && !periodArray[0].isEmpty()) {
                    current.outcome.setStartDate(parseDate(periodArray[0]));
                }
                current.treatment.addOutcome(current.outcome);
            } else if (parameter.startsWith("soil depth") && !getCellStringValue(valueCell).isEmpty()) {
                current.outcome.setSoilDepth(getCellStringValue(valueCell));
            } else if ((parameter.startsWith("mean") || parameter.startsWith("value") ||
                    parameter.startsWith("measure")) && valueCell != null
                    && valueCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                current.outcome.setMeanValue(valueCell.getNumericCellValue());
            } else if (parameter.equals("unit")) {
                current.measureUnitSymbol = getCellStringValue(valueCell);
            } else if (parameter.equals("description")) {
                MeasureUnit unit;
                String measureUnitName = getCellStringValue(valueCell);
                unit = measureUnitRepository.findBySymbolAndName(current.measureUnitSymbol, measureUnitName);

                if (unit == null && !(current.measureUnitSymbol.isEmpty() && measureUnitName.isEmpty())) { //HACK
                    unit = new MeasureUnit();
                    unit.setSymbol(current.measureUnitSymbol);
                    unit.setName(measureUnitName);
                    measureUnitRepository.save(unit);
                }

                current.outcome.setMeasureUnit(unit);
            } else if (parameter.equals("se") && valueCell != null && valueCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                current.outcome.setStandardError(valueCell.getNumericCellValue());

            } else if (parameter.equals("sd") && valueCell != null && valueCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                current.outcome.setStandardDeviation(valueCell.getNumericCellValue());
            }
        }
    }

    private void extractBarrierInformation(String treatmentKey, String parameter, Cell valueCell, DataExtractionState current) {
        if (current.treatments.containsKey(treatmentKey)) {
            current.treatment = current.treatments.get(treatmentKey);

            switch (parameter.split(" ")[0]) {
                case "BarrierCode":
                    Barrier barrier = barrierRepository.getByCode(getCellStringValue(valueCell));
                    if (barrier == null) {
                        barrier = new Barrier();
                        barrier.setCode(getCellStringValue(valueCell));
                        barrierRepository.save(barrier);
                    }
                    current.barrier = new TreatmentBarrier();
                    current.barrier.setBarrier(barrier);
                    current.treatment.addBarrier(current.barrier);
                    break;
                case "quantified":
                    if (valueCell.getCellType() == Cell.CELL_TYPE_NUMERIC && current.barrier != null)
                        current.barrier.setCost(valueCell.getNumericCellValue());
                    break;
                case "unit":
                    String symbol = getCellStringValue(valueCell);
                    MeasureUnit unit = measureUnitRepository.findBySymbolAndName(symbol, "");
                    if (unit == null && !symbol.isEmpty()) { //HACK
                        unit = new MeasureUnit();
                        unit.setSymbol(symbol);
                        unit.setName("");
                        measureUnitRepository.save(unit);
                    }
                    break;
                case "description":
                    if (current.barrier != null)
                        current.barrier.getBarrier().setDescription(
                                Strings.nullToEmpty(current.barrier.getBarrier().getDescription()) + "\n" +
                                        valueCell.getStringCellValue().trim());
            }
        }
    }

    private LocalDate parseDate(String date) {
        try {
            Calendar calendar = new GregorianCalendar();
            String[] dateArray = date.split("/");

            switch (dateArray.length) {
                case 3:
                    calendar.set(Calendar.MONTH, Integer.valueOf(dateArray[0].trim()));
                    calendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(dateArray[1].trim()));
                    calendar.set(Calendar.YEAR, Integer.valueOf(dateArray[2].trim()));
                case 2:
                    calendar.set(Calendar.MONTH, Integer.valueOf(dateArray[0].trim()));
                    calendar.set(Calendar.YEAR, Integer.valueOf(dateArray[1].trim()));
                case 1:
                    calendar.set(Calendar.YEAR, Integer.valueOf(dateArray[0].trim()));
            }
            return LocalDate.fromCalendarFields(calendar);
        } catch (NumberFormatException n) {
            log.warn("Parser issue: " + n.getMessage());
            return null;
        }
    }

    private Float getNumericValue(String value) {
        try {
            String numValue = value.indexOf("m") > 0 ? value.substring(0, value.indexOf("m") - 1) : value;
            return Float.parseFloat(numValue);
        } catch (NumberFormatException n) {
            return 0.0f;
        }
    }

    private String getCellStringValue(Cell cell) {
        if (cell == null) return "";
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            return String.valueOf(Math.round(cell.getNumericCellValue()));
        } else {
            return cell.getStringCellValue().trim();
        }
    }

    @Transactional
    public void extractPracticesInformation(XSSFSheet sheet, boolean noStandard) {

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

            PracticeLevel level = practiceLevelRepository.findByNameAndTheme(practiceLevel, theme);

            if (level == null) {
                level = new PracticeLevel();
                level.setName(practiceLevel);
                level.setTheme(theme);
                level = practiceLevelRepository.save(level);
                level.setCode(String.valueOf(level.getId()));
                practiceLevelRepository.save(level);
            }

            if (practiceRepository.findByCode(practiceCode) == null) {

                Practice newPractice = new Practice();
                newPractice.setCode(practiceCode);
                newPractice.setName(practiceName);
                newPractice.setDescription(practiceDescription);
                newPractice.setPracticeLevel(level);
                newPractice.setActive(noStandard);
                practiceRepository.save(newPractice);
            }
        }
    }

    @Transactional
    public void extractProductionSystemsInformation(XSSFSheet sheet) {
        for (Row row : sheet) {
            Cell cell = row.getCell(0);
            String categoryName = getCellStringValue(cell);

            cell = row.getCell(1);
            String systemCode = getCellStringValue(cell);

            cell = row.getCell(2);
            String systemName = getCellStringValue(cell);

            cell = row.getCell(3);
            String systemDescription = getCellStringValue(cell);

            ProductionSystemCategory category = productionSystemCategoryRepository.findByName(categoryName);

            if (category == null) {
                category = new ProductionSystemCategory();
                category.setName(categoryName);
                category = productionSystemCategoryRepository.save(category);
                category.setCode(String.valueOf(category.getId()));
                category = productionSystemCategoryRepository.save(category);
            }

            ProductionSystem productionSystem = new ProductionSystem();
            productionSystem.setCode(systemCode);
            productionSystem.setName(systemName);
            productionSystem.setCategory(category);
            if (!systemDescription.isEmpty())
                productionSystem.setDescription(systemDescription);

            productionSystemRepository.save(productionSystem);
        }
    }

    @Transactional
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
                IndicatorPillar pillar = new IndicatorPillar(Pillar.getByName(indicatorPillar), 1.0);
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
