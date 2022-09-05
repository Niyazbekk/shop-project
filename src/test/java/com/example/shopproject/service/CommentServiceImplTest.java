package com.example.shopproject.service;

import com.example.shopproject.entity.dto.CommentDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentServiceImplTest {
    //given
    final CommentDto commentDto = CommentDto.builder().id(3L).userId(1L).productId(1L).content("some text").build();
    @MockBean
    CommentServiceImpl commentService;

    @Test
    public void updateCommentTest(){
        //given

        //when
        var mockito = Mockito.when(commentService.updateComment(Mockito.any(CommentDto.class)));

        //then
        mockito.then(returnsFirstArg());

        CommentDto commentForUpdate = CommentDto
                .builder()
                .id(3L)
                .userId(1L)
                .productId(1L)
                .content("another text")
                .build();
        CommentDto result = commentService.updateComment(commentForUpdate);
        assertEquals(result, commentForUpdate);
    }

    @Test
    public void createCommentTest() {
        //given

        //when
        var mockito = Mockito.when(commentService.createComment(Mockito.any(CommentDto.class)));

        //then
        mockito.then(returnsFirstArg());

        CommentDto result = commentService.createComment(commentDto);
        assertEquals(result, commentDto);
    }

    @Test
    public void getCommentByIdTest() {
        //given

        //when
        var mockito = Mockito.when(commentService.getCommentById(anyLong()));

        //then
        mockito.thenReturn(commentDto);

        CommentDto result = commentService.getCommentById(3L);
        assertEquals(result, commentDto);
    }

    @Test
    public void deleteBasket() {
        //given

        //when

        //then

        commentService.deleteCommentById(3L);
        verify(commentService).deleteCommentById(3L);
    }
}