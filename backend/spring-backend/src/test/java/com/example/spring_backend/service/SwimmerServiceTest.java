package com.example.spring_backend.service;

import com.example.spring_backend.DTOs.SwimmerDTO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

import static com.example.spring_backend.model.Especiality.middle;
import static com.example.spring_backend.model.Sex.male;
import static com.example.spring_backend.model.Stroke.sf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@AutoConfigureMockMvc
public class SwimmerServiceTest {

    @Container
    static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:16");


    @Autowired
    private MockMvc mockMvc ;




    @DynamicPropertySource
    static void configurations(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);

    }
    private static String asJsonString(Object obj) throws Exception {
        return new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(obj);
    }
    @Test
    void addSwimmer() throws Exception {
        SwimmerDTO dto = new SwimmerDTO(10L,20,middle,"hesho","hesham@gmail.com","Heshoo1990@",male,sf);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/swimmers")
                .contentType("application/json")
                .content(asJsonString(dto))
                 .accept("application/json"))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
        ;
        String loginPayload = "{\"email\":\"hesham@gmail.com\",\"password\":\"Heshoo1990@\"}";


        String token = mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType("application/json")
                        .content(loginPayload))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

// token is now the raw JWT string, no parsing neededo match your response

        // 3. Hit a protected endpoint with the token
        mockMvc.perform(MockMvcRequestBuilders.get("/api/dailylogs")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());

    }

}


