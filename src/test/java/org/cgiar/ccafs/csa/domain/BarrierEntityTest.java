package org.cgiar.ccafs.csa.domain;

import org.cgiar.ccafs.csa.CSAToolApplication;
import org.cgiar.ccafs.csa.domain.workshop.WorkshopBarrier;
import org.cgiar.ccafs.csa.repository.BarrierRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CSAToolApplication.class)
@WebIntegrationTest
public class BarrierEntityTest {

    @Autowired
    private BarrierRepository repo;

    private Barrier barrier1;

    private WorkshopBarrier barrier2;

    private boolean initialized;

    @Before
    public void setUp() {
        if (!initialized) {
            barrier1 = new Barrier();
            barrier1.setCode("b1");
            barrier1.setName("Bad economics");

            barrier2 = new WorkshopBarrier();
            barrier2.setCode("b2");
            barrier2.setName("Internal conflict");

            repo.save(barrier1);
            repo.save(barrier2);
            initialized = true;
        }
    }

    @Test
    public void canFetchBarrier1() {
        Integer barrier1Id = barrier1.getId();

        org.junit.Assert.assertTrue(repo.exists(barrier1Id));

        org.junit.Assert.assertEquals(barrier1.getName(), repo.findByName("Bad economics").get(0).getName());
    }

    @Test
    public void canFetchCompendiumBarrier() {
        List<Barrier> barriers = repo.findCompendiumBarriers();
        org.junit.Assert.assertTrue(barriers.size() > 1);
        org.junit.Assert.assertTrue(barriers.get(0).belongsToCompendium());
    }

}
