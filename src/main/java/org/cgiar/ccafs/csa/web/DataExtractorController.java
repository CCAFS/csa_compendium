package org.cgiar.ccafs.csa.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.cgiar.ccafs.csa.domain.ExperimentArticle;
import org.cgiar.ccafs.csa.domain.ExperimentLocation;
import org.cgiar.ccafs.csa.repository.ExperimentArticleRepository;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class DataExtractorController {
	
	private final Logger log = LoggerFactory.getLogger(DataExtractorController.class);
	
	@RequestMapping(value="/data_extractor", method=RequestMethod.POST)
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("excel-uploaded.xls")));
                stream.write(bytes);
                stream.close();
                
                processUploadedData();
            } catch (Exception e) {
                log.error("Failed to upload file" , e.getMessage());
            }
        } else {
        	log.error("Failed to upload file, was empty.");
        }
        
        return "redirect:/admin/";
    }
	
	@Autowired
	ExperimentArticleRepository rep1;

	private void processUploadedData() {
		ExperimentArticle art = new ExperimentArticle();
		art.setCode("AP0087");
		art.setTitle("Cost-benefit analysis of alternative forms of hedgerow intercropping in the Philippine uplands.");
		art.addAuthor("Nelson R A");
		art.addContact("rnelson@aaa.net.au");
		
		rep1.save(art);
				
		//ExperimentLocation loc = new ExperimentLocation();
		//loc.setLongitude(longitude);			
		
		
	}	

}