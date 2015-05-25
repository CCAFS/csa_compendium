package org.cgiar.ccafs.csa.domain;

import org.cgiar.ccafs.csa.CSAToolApplication;
import org.cgiar.ccafs.csa.repository.PracticeLevelRepository;
import org.cgiar.ccafs.csa.repository.PracticeThemeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CSAToolApplication.class)
@WebAppConfiguration
//@IntegrationTest("server.port:0")
public class AbstractInformationEntityTest {

    @Autowired
    PracticeThemeRepository repositorioT;

    @Autowired
    PracticeLevelRepository repositorioL;

    PracticeTheme theme1;
    PracticeLevel level1;
    PracticeLevel level2;

    //@Value("${local.server.port}")
    //int port;

    @Before
    public void setUp() {
        theme1 = new PracticeTheme();
        theme1.setCode("1");
        theme1.setName("Agronomy");

        level1 = new PracticeLevel();
        level1.setCode("b3");
        level1.setName("Crop Rotations");
        theme1.addPracticeLevel(level1);

        level2 = new PracticeLevel();
        level2.setCode("b7");
        level2.setName("Diversification");
        theme1.addPracticeLevel(level2);

        repositorioL.deleteAll();
        repositorioT.deleteAll();

        repositorioT.save(theme1);

        repositorioL.save(level1);
        repositorioL.save(level2);

    }

    @Test
    public void canFetchTheme1() {
        Integer themeId = theme1.getId();

        org.junit.Assert.assertTrue(repositorioT.exists(themeId));

        //org.junit.Assert.assertEquals(theme1.getName(), repositorioT.findByName("Agronomy").get(0).getName());


    }

    @Test
    public void canFechChildren() {
        org.junit.Assert.assertEquals(2, repositorioL.count());

        for (PracticeLevel p : theme1.getPracticeLevels()) {
            org.junit.Assert.assertTrue(repositorioL.exists(p.getId()));
        }
    }
}
