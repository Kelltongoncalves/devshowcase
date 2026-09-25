package br.com.knw.devshowcase.controller;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;



import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;

import org.springframework.test.context.bean.override.mockito.MockitoBean;

import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;

import br.com.knw.devshowcase.dto.project.ProjectResponseDTO;

import br.com.knw.devshowcase.service.ProjectService;

import java.util.List;



@SpringBootTest

@AutoConfigureMockMvc

class ProjectControllerTest {

@Autowired private MockMvc mockMvc;

@MockitoBean private ProjectService projectService;



@Test

void deveCriarProjeto() throws Exception {

when(projectService.create(any())).thenReturn(new ProjectResponseDTO(1L,"DevShowcase API","API","https://github.com/teste",1L,List.of(1L,2L),0,0.0));

String json = """

{"title":"DevShowcase API","description":"API","url":"https://github.com/teste","profileId":1,"technologyIds":[1,2]}

""";

mockMvc.perform(post("/api/projects").contentType(MediaType.APPLICATION_JSON).content(json))

.andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(1));

}



@Test

void deveListarProjetos() throws Exception {

when(projectService.findAll(any(), any())).thenReturn(new org.springframework.data.domain.PageImpl<>(List.of(

new ProjectResponseDTO(1L,"DevShowcase API","API","https://github.com/teste",1L,List.of(1L),2,4.5))));

mockMvc.perform(get("/api/projects?page=0&size=10")).andExpect(status().isOk()).andExpect(jsonPath("$.content[0].title").value("DevShowcase API"));

}



@Test

void deveFiltrarPorTecnologia() throws Exception {

when(projectService.findAll(any(), any())).thenReturn(new org.springframework.data.domain.PageImpl<>(List.of()));

mockMvc.perform(get("/api/projects?technology=Java&page=0&size=10")).andExpect(status().isOk());

}



@Test

void deveDarUpvote() throws Exception {

when(projectService.upvote(1L)).thenReturn(new ProjectResponseDTO(1L,"Projeto","Desc","https://teste.com",1L,List.of(),3,0.0));

mockMvc.perform(put("/api/projects/1/upvote")).andExpect(status().isOk()).andExpect(jsonPath("$.upvotes").value(3));

}



@Test

void deveRecusarProjetoComTituloVazio() throws Exception {

String json = """
			{"title":"","description":"Teste","url":"https://github.com/teste","profileId":1,"technologyIds":[]}
			""";

mockMvc.perform(post("/api/projects").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest());

}



@Test

void deveRecusarProjetoComUrlInvalida() throws Exception {

	String json = """
            {"title":"Projeto Teste","description":"Teste","url":"url-invalida","profileId":1,"technologyIds":[]}
            """;

mockMvc.perform(post("/api/projects").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest());

}

} 

