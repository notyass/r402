package com.example.demo.data;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class VoitureTest {

    @Test
    void creerVoiture(){
        Voiture voiture = new Voiture("Renault", 100000);
        Assert.isTrue(voiture.getMarque().equals("Renault"), "Doit être Renault");
        Assert.isTrue(voiture.getPrix() == 100000, "Doit être 100000");
        Assert.isTrue(voiture.getId() == 0, "Doit être 0");
    }

}
