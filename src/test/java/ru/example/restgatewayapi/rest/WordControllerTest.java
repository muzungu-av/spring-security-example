package ru.example.restgatewayapi.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * Несколько тестов слоя Controller
 */
@SpringBootTest
@AutoConfigureMockMvc
class WordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Тест на доступ к закрытому ресурсу неавторизованным пользователем.
     */
    @Test
    public void shouldReturnUnauthorizedMessage() throws Exception {
        this.mockMvc.perform(get("/api/v1/auth/word")).andDo(print()).andExpect(status().isUnauthorized())
                .andExpect(content().string(containsString("error.access")));
    }
}