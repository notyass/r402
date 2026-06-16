package com.example.demo.data;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class VoitureTest {

    @Test
    void creerVoiture() {

        // 
        String marqueAttendue = "Renault";
        int prixAttendu = 100000;

        
        Voiture voiture = new Voiture(marqueAttendue, prixAttendu);

        
        assertEquals(marqueAttendue, voiture.getMarque(), "La marque doit être Renault");
        assertEquals(prixAttendu, voiture.getPrix(), "Le prix doit être 100000");
        assertEquals(0, voiture.getId(), "L'id par défaut doit être 0");
    }

    @Test
    void testGetId() {
        // Given
        Voiture voiture = new Voiture("Peugeot", 25000);
        int nouvelId = 42;

        // When
        voiture.setId(nouvelId);

        // Then
        assertEquals(nouvelId, voiture.getId(), "L'id récupéré doit correspondre à l'id défini");
    }

    @Test
    void testSetMarque() {
        // Given
        Voiture voiture = new Voiture("Ancienne Marque", 15000);

        // When
        voiture.setMarque("Nouvelle Marque");

        // Then
        assertEquals("Nouvelle Marque", voiture.getMarque());
    }

    @Test
    void testSetPrix() {
        // Given
        Voiture voiture = new Voiture("Dacia", 12000);

        // When
        voiture.setPrix(13000);

        // Then
        assertEquals(13000, voiture.getPrix());
    }

    @Test
    void testToString() {
        // Given
        Voiture voiture = new Voiture("Fiat", 8000);
        voiture.setId(5);

        // When
        String resultat = voiture.toString();

        // Then
        assertNotNull(resultat);
        assertEquals("Car{marque='Fiat', prix=8000, id=5}", resultat);
    }
}