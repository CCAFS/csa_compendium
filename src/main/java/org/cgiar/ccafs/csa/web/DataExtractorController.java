package org.cgiar.ccafs.csa.web;

import org.cgiar.ccafs.csa.domain.ExperimentArticle;
import org.cgiar.ccafs.csa.repository.ExperimentArticleRepository;
import org.cgiar.ccafs.csa.repository.FarmingSystemRepository;
import org.cgiar.ccafs.csa.repository.ThemeRepository;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Controller
public class DataExtractorController {

    private final Logger log = LoggerFactory.getLogger(DataExtractorController.class);

    @Autowired
    private ExperimentArticleRepository rep1;

    @Autowired
    private FarmingSystemRepository rep2;

    @Autowired
    private ThemeRepository rep3;

    @RequestMapping(value = "/data_extractor", method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("excel-uploaded.xls")));
                stream.write(bytes);
                stream.close();

                processUploadedData();
            } catch (Exception e) {
                log.error("Failed to upload file", e.getMessage());
            }
        } else {
            log.error("Failed to upload file, was empty.");
        }

        return "redirect:/admin/";
    }


    private void processUploadedData() {
        ExperimentArticle art = new ExperimentArticle();
        art.setCode("AP0087");
        art.setTitle("Cost-benefit analysis of alternative forms of hedgerow intercropping in the Philippine uplands.");
        art.addAuthor("Nelson R A");
        art.addContact("rnelson@aaa.net.au");
        art.setPublicationDate(new LocalDate(1998, 5, 10));
        art.setFarmingSystem(rep2.findByCode("e4"));
        art.setTheme(rep3.findOne(1));

        rep1.save(art);

        //ExperimentLocation loc = new ExperimentLocation();
        //loc.setLongitude(longitude);


    }

}
