package org.cgiar.ccafs.csa.web;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.cgiar.ccafs.csa.domain.ExperimentContext;
import org.cgiar.ccafs.csa.domain.Treatment;
import org.cgiar.ccafs.csa.domain.TreatmentOutcome;
import org.cgiar.ccafs.csa.domain.workshop.WorkshopExperiment;
import org.cgiar.ccafs.csa.repository.ExperimentContextRepository;
import org.cgiar.ccafs.csa.repository.TreatmentOutcomeRepository;
import org.cgiar.ccafs.csa.repository.TreatmentRepository;
import org.cgiar.ccafs.csa.repository.workshop.WorkshopExperimentRepository;
import org.cgiar.ccafs.csa.service.DataExtractionService;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
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
import javax.transaction.Transactional;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static com.google.common.base.Strings.isNullOrEmpty;

@ManagedBean
@Controller
public class DataExtractorController {

    private final Logger log = LoggerFactory.getLogger(DataExtractorController.class);

    private String result = "";

    private WorkshopExperiment experiment;
    private ExperimentContext currentContext;
    private Integer currentContextId;
    private Treatment currentTreatment;
    private Integer currentTreatmentId;
    private TreatmentOutcome currentOutcome;
    private Integer currentOutcomeId;
    private boolean experimentInitialized;
    private boolean experimentContextsInitialized;
    private boolean experimentTreatmentsInitialized;
    private boolean experimentOutcomesInitialized;
    private String formLocation = "/admin/domain/workshopExperiments";

    @Autowired
    private DataExtractionService dataService;

    @Autowired
    private WorkshopExperimentRepository workshopExperimentRepository;

    @Autowired
    private ExperimentContextRepository experimentContextRepository;

    @Autowired
    private TreatmentRepository treatmentRepository;

    @Autowired
    private TreatmentOutcomeRepository treatmentOutcomeRepository;

    @Value("${csa.tmp-dir}")
    private String tempStorageLocation;

    @RequestMapping(value = "/data_extractor", method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                Workbook book = WorkbookFactory.create(file.getInputStream());
                dataService.extractArticleInformation(book.getSheetAt(0));
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
            UploadedFile file = event.getFile();

            if (file.getFileName().endsWith("zip")) {
                File tempFile = new File(tempStorageLocation, file.getFileName());
                byte[] bytes = file.getContents();
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
                        dataService.extractArticleInformation(sheet);
                    } catch (InvalidFormatException i) {
                        log.warn("Not a supported format: " + zipEntry.getName());
                    } catch (Exception e) {
                        log.error("Fail to load: " + zipEntry.getName() + " - " + e.getMessage());
                        //log.error("Fail to load: " + zipEntry.getName() + " - ", e);
                    }
                }

                zipFile.close();
                if (!tempFile.delete()) {
                    log.warn("Unable to remove temporary file: " + file.getFileName());
                }
            } else {
                XSSFWorkbook workbook = new XSSFWorkbook(file.getInputstream());
                XSSFSheet sheet = workbook.getSheetAt(0);

                if (file.getFileName().equals("Practices.xlsx")) {
                    dataService.extractPracticesInformation(sheet, true);
                } else if (file.getFileName().equals("OldPractices.xlsx")) {
                    dataService.extractPracticesInformation(sheet, false);
                } else if (file.getFileName().equals("Indicators.xlsx")) {
                    dataService.extractIndicatorsInformation(sheet);
                } else if (file.getFileName().equals("ProductionSystems.xlsx")) {
                    dataService.extractProductionSystemsInformation(sheet);
                } else {
                    dataService.extractArticleInformation(sheet);
                }
                workbook.close();
            }
            result += file.getFileName() + "  ";
        } catch (Exception e) {
            log.error("Can't process the file.", e);
            result = "Things didn't went well. Check logs.";
        }
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void createNewExperiment() {
        experiment = workshopExperimentRepository.save(new WorkshopExperiment());
        formLocation = "/admin/domain/workshopExperiments/" + experiment.getId() + "/edit";
    }

    @Transactional
    public void createNewContext() {
        currentContext = new ExperimentContext();
        experiment.addContext(currentContext);
        currentContext = experimentContextRepository.save(currentContext);
        currentContextId = currentContext.getId();
        formLocation = "/admin/domain/experimentContexts/" + currentContext.getId() + "/edit";
    }

    @Transactional
    public void createNewTreatment() {
        currentTreatment = new Treatment();
        currentContext = experimentContextRepository.findOne(currentContextId);
        currentContext.addTreatment(currentTreatment);
        currentTreatment = treatmentRepository.save(currentTreatment);
        currentTreatmentId = currentTreatment.getId();
        formLocation = "/admin/domain/treatments/" + currentTreatment.getId() + "/edit";
    }

    @Transactional
    public void createNewOutcome() {
        currentOutcome = new TreatmentOutcome();
        currentTreatment = treatmentRepository.findOne(currentTreatmentId);
        currentTreatment.addOutcome(currentOutcome);
        currentOutcome = treatmentOutcomeRepository.save(currentOutcome);
        currentOutcomeId = currentOutcome.getId();
        formLocation = "/admin/domain/treatmentOutcomes/" + currentOutcome.getId() + "/edit";
    }

    @Transactional
    public void processInput() {
        if (experiment != null) {
            experiment = workshopExperimentRepository.findOne(experiment.getId());
            experimentInitialized = experimentInitialized || !isNullOrEmpty(experiment.getCode());
            experiment.getContexts().size();
        }

        if (experimentInitialized && currentContext != null) {
            currentContext = experimentContextRepository.findOne(currentContextId);
            experimentContextsInitialized = experimentContextsInitialized || currentContext.getFarmingSystem() != null;
            currentContext.getTreatments().size();
        }

        if (experimentContextsInitialized && currentTreatment != null) {
            currentTreatment = treatmentRepository.findOne(currentTreatmentId);
            experimentTreatmentsInitialized = experimentTreatmentsInitialized || !isNullOrEmpty(currentTreatment.getInformation());
            currentTreatment.getOutcomes().size();
        }

        if (experimentTreatmentsInitialized && currentOutcome != null) {
            currentOutcome = treatmentOutcomeRepository.findOne(currentOutcomeId);
            experimentOutcomesInitialized = experimentOutcomesInitialized || !isNullOrEmpty(currentOutcome.getResult())
                    || currentOutcome.getMeanValue() != 0.0;
        }
    }

    public String getFormLocation() {
        return formLocation;
    }

    public WorkshopExperiment getExperiment() {
        return experiment;
    }

    public boolean isExperimentInitialized() {
        return experimentInitialized;
    }

    public ExperimentContext getCurrentContext() {
        return currentContext;
    }

    public Integer getCurrentContextId() {
        return currentContextId;
    }

    public void setCurrentContextId(Integer currentContextId) {
        this.currentContextId = currentContextId;
    }

    public boolean isExperimentContextsInitialized() {
        return experimentContextsInitialized;
    }

    @Transactional
    public Treatment getCurrentTreatment() {
        if (currentTreatment != null) currentTreatment.getOutcomes().size();
        return currentTreatment;
    }

    public Integer getCurrentTreatmentId() {
        return currentTreatmentId;
    }

    public void setCurrentTreatmentId(Integer currentTreatmentId) {
        this.currentTreatmentId = currentTreatmentId;
    }

    public boolean isExperimentTreatmentsInitialized() {
        return experimentTreatmentsInitialized;
    }

    public Integer getCurrentOutcomeId() {
        return currentOutcomeId;
    }

    public void setCurrentOutcomeId(Integer currentOutcomeId) {
        this.currentOutcomeId = currentOutcomeId;
    }

    public boolean isExperimentOutcomesInitialized() {
        return experimentOutcomesInitialized;
    }
}
