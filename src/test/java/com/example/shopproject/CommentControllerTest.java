package com.example.shopproject;

import com.example.shopproject.controller.CommentController;
import com.example.shopproject.entity.dto.CommentDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentControllerTest {
    static CommentDto commentDto = CommentDto.builder().id(3L).userId(1L).productId(1L).content("some text").build();

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CommentController commentController;

    @Test
    void postTestComment() throws Exception {
        Mockito.when(commentController.createComment(commentDto)).thenReturn(commentDto);

        MvcResult result = mockMvc.perform(post("/api/v1/comment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(commentDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertThat(result).isNotNull();
        String json = result.getResponse().getContentAsString();
        Assertions.assertThat(json).isNotEmpty();
    }

    @Test
    void getCommentById() throws Exception {
        Mockito.when(commentController.getCommentById(3L)).thenReturn(commentDto);

        MvcResult result = mockMvc.perform(get("http://localhost:8080/api/v1/comment/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
        Assertions.assertThat(result).isNotNull();
        String userJson2 = result.getResponse().getContentAsString();
        Assertions.assertThat(userJson2).isEqualToIgnoringCase(asJsonString(commentDto));
    }

    @Test
    void getComments() throws Exception {
        MvcResult result = mockMvc.perform(get("http://localhost:8080/api/v1/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
        Assertions.assertThat(result).isNotNull();
        String userJson2 = result.getResponse().getContentAsString().trim();
        userJson2 = StringUtils.substringBetween(userJson2, "[", "]");
        Assertions.assertThat(userJson2).isEqualToIgnoringCase(asJsonString(commentDto));
    }

    @Test
    void deleteComment() throws Exception {
        this.mockMvc.perform(delete("/api/v1/comment/3")).andDo(print())
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
