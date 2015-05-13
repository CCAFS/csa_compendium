package org.cgiar.ccafs.csa.web;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.cgiar.ccafs.csa.service.DataExtractionService;
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

    private String result = "";

    @Autowired
    private DataExtractionService dataService;

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
                        dataService.extractArticleInformation(sheet);
                    } catch (InvalidFormatException i) {
                        log.warn("Not a supported format: " + zipEntry.getName());
                    } catch (Exception e) {
                        log.error("Fail to load: " + zipEntry.getName(), e);
                    }
                }

                zipFile.close();
                if (!tempFile.delete()) {
                    log.warn("Unable to remove temporary file: " + file.getName());
                }
            } else {
                XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
                XSSFSheet sheet = workbook.getSheetAt(0);

                if (file.getName().equals("Practices.xlsx")) {
                    dataService.extractPracticesInformation(sheet);
                } else if (file.getName().equals("Indicators.xlsx")) {
                    dataService.extractIndicatorsInformation(sheet);
                } else {
                    dataService.extractArticleInformation(sheet);
                }
                workbook.close();
            }
            result += file.getName() + "  ";
        } catch (IOException e) {
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
}
