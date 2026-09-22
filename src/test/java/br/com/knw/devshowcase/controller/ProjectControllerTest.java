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
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import br.com.knw.devshowcase.service.ProjectService;


@SpringBootTest
@AutoConfigureMockMvc
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockitoBean
    private ProjectService projectService;

    @Test
    void deveCriarProjeto() throws Exception {

        String json = """
                {
                    "title": "DevShowcase API",
                    "description": "API desenvolvida com Java e Spring Boot",
                    "url": "https://github.com/kelton/devshowcase",
                    "profileId": 1,
                    "technologyIds": [1, 2, 3]
                }
                """;

        mockMvc.perform(post("/api/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void deveListarProjetos() throws Exception {

        mockMvc.perform(get("/api/projects"))
                .andExpect(status().isOk());
    }

    @Test
    void deveRecusarProjetoComTituloVazio() throws Exception {

        String json = """
                {
                    "title": "",
                    "description": "Teste",
                    "url": "https://github.com/teste",
                    "profileId": 1,
                    "technologyIds": []
                }
                """;

        mockMvc.perform(post("/api/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRecusarProjetoComUrlInvalida() throws Exception {

        String json = """
                {
                    "title": "Projeto Teste",
                    "description": "Teste",
                    "url": "url-invalida",
                    "profileId": 1,
                    "technologyIds": []
                }
                """;

        mockMvc.perform(post("/api/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());
    }
}