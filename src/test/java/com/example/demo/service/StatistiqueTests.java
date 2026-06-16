package com.example.demo.service;

import com.example.demo.data.Voiture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatistiqueTests {

    private StatistiqueImpl statistique;

    @BeforeEach
    void setUp() {
        // Initialisation d'une nouvelle instance avant chaque test 
        // pour garantir que la liste interne 'voitures' est vide au départ.
        statistique = new StatistiqueImpl();
    }

    @Test
    void testPrixMoyenAvecUneVoiture() {
        // 1. Arrange (Préparation des données)
        Voiture ferrari = new Voiture("Ferrari", 5000);

        // 2. Act (Exécution de l'action à tester)
        statistique.ajouter(ferrari);
        Echantillon resultat = statistique.prixMoyen();

        // 3. Assert (Vérifications)
        assertNotNull(resultat, "L'échantillon ne devrait pas être nul");
        assertEquals(1, resultat.getNombreDeVoitures(), "Le nombre de voitures doit être de 1");
        // On s'attend à 5000 / 1 = 5000 (Le calcul ou la structure dépend de ce que stocke/calcule votre classe Echantillon)
        // Si votre classe Echantillon possède une méthode getPrixMoyen(), vous pouvez aussi la tester ici.
    }

    @Test
    void testPrixMoyenAvecPlusieursVoitures() {
        // Arrange
        Voiture voiture1 = new Voiture("Ferrari", 5000);
        Voiture voiture2 = new Voiture("Clio", 1000);

        // Act
        statistique.ajouter(voiture1);
        statistique.ajouter(voiture2);
        Echantillon resultat = statistique.prixMoyen();

        // Assert
        assertNotNull(resultat);
        assertEquals(2, resultat.getNombreDeVoitures(), "Le nombre de voitures doit être de 2");
        // Le prix moyen théorique est (5000 + 1000) / 2 = 3000
    }

    @Test
    void testPrixMoyenLanceUneExceptionSiAucuneVoiture() {
        // Act & Assert
        // Comme la liste 'voitures' est vide au départ, nombreDeVoitures vaut 0.
        // La ligne `prixTotal/nombreDeVoitures` va provoquer une division par zéro (ArithmeticException).
        assertThrows(ArithmeticException.class, () -> {
            statistique.prixMoyen();
        }, "Une ArithmeticException aurait dû être levée à cause de la division par zéro");
    }
}