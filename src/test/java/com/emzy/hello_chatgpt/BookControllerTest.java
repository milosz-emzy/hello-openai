package com.emzy.hello_chatgpt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService service;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void shouldReturnBook() throws Exception {
        Book b = new Book(
                "author-1",
                "title-1",
                "Summary",
                "Cover is nice",
                100,
                2015
        );

        when(service.getBook()).thenReturn(b);
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/"))
                .andDo(print()).andExpect(status().isOk())
                .andReturn();

        Book book = objectMapper.readValue(result.getResponse().getContentAsString(), Book.class);
        Assertions.assertEquals(b, book);
    }
}