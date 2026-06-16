package com.example.demo.web;

import com.example.demo.data.Voiture;
import com.example.demo.service.Echantillon;
import com.example.demo.service.StatistiqueImpl;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class WebTests {

    @MockBean
    StatistiqueImpl statistiqueImpl;

    @Autowired
    MockMvc mockMvc;

    // Test de la requête POST (correspond à la commande curl de l'énoncé)
    @Test
    void testCreerVoiture() throws Exception {
        String jsonBody = "{\"marque\":\"f\",\"prix\":100}";

        mockMvc.perform(post("/voiture")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andDo(print())
                .andExpect(status().isOk());

        // On vérifie que la méthode ajouter() du mock a bien été appelée 1 fois
        verify(statistiqueImpl, times(1)).ajouter(any(Voiture.class));
    }

    // Test de la requête GET en cas de succès
    @Test
    void testGetStatistiques_Success() throws Exception {
        Echantillon mockEchantillon = new Echantillon(); // Remplacer par un constructeur valide si nécessaire
        when(statistiqueImpl.prixMoyen()).thenReturn(mockEchantillon);

        mockMvc.perform(get("/statistique"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(statistiqueImpl, times(1)).prixMoyen();
    }

    // Test de la requête GET quand une ArithmeticException est levée
    @Test
    void testGetStatistiques_ArithmeticException() throws Exception {
        // On configure le mock pour simuler l'erreur de division par zéro
        when(statistiqueImpl.prixMoyen()).thenThrow(new ArithmeticException());

        // On vérifie que l'ArithmeticException est bien capturée par le bloc try/catch 
        // du contrôleur et relancée en tant que PasDeVoitureException
        mockMvc.perform(get("/statistique"))
                .andDo(print())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof PasDeVoitureException));
    }
}