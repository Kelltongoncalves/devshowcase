package br.com.knw.devshowcase.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test; // <-- JUnit 5 obrigatoriamente
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
@SpringBootTest
@AutoConfigureMockMvc
class TechnologyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveCriarTecnologia() throws Exception {

        String json = """
                {
                    "name": "Java"
                }
                """;

        mockMvc.perform(post("/api/technologies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void deveListarTecnologias() throws Exception {

        mockMvc.perform(get("/api/technologies"))
                .andExpect(status().isOk());
    }

    @Test
    void deveRecusarTecnologiaSemNome() throws Exception {

        String json = """
                {
                    "name": ""
                }
                """;

        mockMvc.perform(post("/api/technologies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());
    }
}