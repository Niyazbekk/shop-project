package com.example.shopproject;

import com.example.shopproject.entity.dto.CommentDto;
import com.example.shopproject.service.CommentServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentServiceImplTest {
    static CommentDto commentDto = CommentDto.builder().id(3L).userId(1L).productId(1L).content("some text").build();
    @MockBean
    CommentServiceImpl commentService;

    @Test
    public void updateCommentTest(){
        Mockito.when(commentService.updateComment(Mockito.any(CommentDto.class))).then(returnsFirstArg());
        CommentDto commentForUpdate = CommentDto
                .builder()
                .id(3L)
                .userId(1L)
                .productId(1L)
                .content("another text")
                .build();
        CommentDto result = commentService.updateComment(commentForUpdate);
        assertNotNull(result);
        assertSame(result.getId(), commentForUpdate.getId());
        assertEquals(result.getUserId(), commentForUpdate.getUserId());
        assertEquals(result.getProductId(), commentForUpdate.getProductId());
    }

    @Test
    public void createCommentTest() {
        Mockito.when(commentService.createComment(Mockito.any(CommentDto.class))).then(returnsFirstArg());
        CommentDto result = commentService.createComment(commentDto);
        assertNotNull(result);
        assertSame(result.getId(), commentDto.getId());
        assertEquals(result.getUserId(), commentDto.getUserId());
        assertEquals(result.getProductId(), commentDto.getProductId());
    }

    @Test
    public void getCommentByIdTest() {
        Mockito.when(commentService.getCommentById(anyLong())).thenReturn(commentDto);
        CommentDto result = commentService.getCommentById(3L);
        assertNotNull(result);
        assertSame(result.getId(), commentDto.getId());
        assertEquals(result.getUserId(), commentDto.getUserId());
        assertEquals(result.getProductId(), commentDto.getProductId());
    }

    @Test
    public void deleteBasket() {
        commentService.deleteCommentById(3L);
        verify(commentService).deleteCommentById(3L);
    }
}