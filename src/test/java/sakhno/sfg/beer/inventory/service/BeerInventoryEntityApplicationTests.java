package sakhno.sfg.beer.inventory.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = {"classpath:application.properties", "classpath:bootstrap.properties"})
public class BeerInventoryEntityApplicationTests {

    @Test
    public void contextLoads() {
    }

}
