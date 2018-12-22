package ru.veryevilzed.tools.rng.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.veryevilzed.tools.rng.MersennePoolConfiguration;
import ru.veryevilzed.tools.rng.services.MersennePoolService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes ={MersennePoolConfiguration.class} )
@Import(TestApplication.class)
@ActiveProfiles("test")
@TestPropertySource(properties = "debug=false")
public class MersennePoolServiceTest {

    @Autowired
    MersennePoolService service;

    @Test
    public void TestPoolSize() {
        service.get();
        service.get();
        service.get();
        Assert.assertEquals(service.size(), 100);
    }


    @Test
    public void TestItems() {
        Assert.assertNotEquals(service.get(), service.get());
    }
}
