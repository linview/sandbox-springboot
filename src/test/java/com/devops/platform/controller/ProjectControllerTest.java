package com.devops.platform.controller;

import com.devops.platform.entity.Project;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.yml")
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateProject() throws Exception {
        Project project = new Project();
        project.setName("Test Project");
        project.setDescription("Test project description");

        MvcResult result = mockMvc.perform(post("/api/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(project)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Project"))
                .andExpect(jsonPath("$.description").value("Test project description"))
                .andReturn();

        System.out.println("CREATE PROJECT RESPONSE: " + result.getResponse().getContentAsString());
    }

    @Test
    void testGetAllProjects() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/projects"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andReturn();

        System.out.println("GET ALL PROJECTS RESPONSE: " + result.getResponse().getContentAsString());
    }

    @Test
    void testGetProjectById() throws Exception {
        // First create a project
        Project project = new Project();
        project.setName("Test Project for ID");
        project.setDescription("Test project for ID lookup");

        MvcResult createResult = mockMvc.perform(post("/api/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(project)))
                .andExpect(status().isOk())
                .andReturn();

        Project createdProject = objectMapper.readValue(createResult.getResponse().getContentAsString(), Project.class);

        // Then get it by ID
        MvcResult getResult = mockMvc.perform(get("/api/projects/" + createdProject.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Project for ID"))
                .andReturn();

        System.out.println("GET PROJECT BY ID RESPONSE: " + getResult.getResponse().getContentAsString());
    }

    @Test
    void testSearchProjects() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/projects/search?keyword=test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andReturn();

        System.out.println("SEARCH PROJECTS RESPONSE: " + result.getResponse().getContentAsString());
    }

    @Test
    void testProjectExists() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/projects/exists/TestProject"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isBoolean())
                .andReturn();

        System.out.println("PROJECT EXISTS RESPONSE: " + result.getResponse().getContentAsString());
    }
}