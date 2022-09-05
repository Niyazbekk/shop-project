package com.example.shopproject.repository;

import com.example.shopproject.entity.Comment;
import com.example.shopproject.entity.Product;
import com.example.shopproject.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CommentRepositoryTest {
    //given
    @MockBean
    private CommentRepository commentRepository;
    private final Comment comment = Comment.builder().id(1L).user(new User()).content("content").product(new Product()).build();
    private final List<Comment> comments = Collections.singletonList(comment);

    @Before
    public void init() {
        commentRepository.save(comment);
    }

    @Test
    public void getCommentByIdTest() {
        //given

        //when
        var mockito = when(commentRepository.getReferenceById(anyLong()));

        //then
        mockito.thenReturn(comment);

        Comment result = commentRepository.getReferenceById(1L);
        assertEquals(result, comment);
    }

    @Test
    public void getAllCommentsTest() {
        //given

        //when
        var mockito = when(commentRepository.findAll());

        //then
        mockito.thenReturn(comments);

        List<Comment> result = commentRepository.findAll();
        assertEquals(result.get(0), comment);
    }

    @Test
    public void deleteCommentTest() {
        //given

        //when

        //then

        commentRepository.delete(comment);
        verify(commentRepository).delete(comment);
    }
}
