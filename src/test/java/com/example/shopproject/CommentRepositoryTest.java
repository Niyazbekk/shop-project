package com.example.shopproject;

import com.example.shopproject.entity.Comment;
import com.example.shopproject.entity.Product;
import com.example.shopproject.entity.User;
import com.example.shopproject.repository.CommentRepository;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CommentRepositoryTest {
    @MockBean
    private CommentRepository commentRepository;
    static Comment comment;
    static List<Comment> comments;

    @BeforeClass
    public static void prepareTestData() {
        comment = Comment.builder().id(1L).user(new User()).content("content").product(new Product()).build();
        comments = Collections.singletonList(comment);
    }

    @Before
    public void init() {
        commentRepository.save(comment);
    }

    @Test
    public void getCommentByIdTest() {
        when(commentRepository.getReferenceById(anyLong())).thenReturn(comment);
        Comment result = commentRepository.getReferenceById(1L);
        assertNotNull(result);
        assertEquals(result.getId(), comment.getId());
        assertEquals(result.getProduct(), comment.getProduct());
        assertEquals(result.getUser(), comment.getUser());
        assertEquals(result.getContent(), comment.getContent());
    }

    @Test
    public void getAllCommentsTest() {
        commentRepository.save(comment);
        when(commentRepository.findAll()).thenReturn(comments);
        List<Comment> result = commentRepository.findAll();
        System.out.println(result);
        assertNotNull(result);
        assertEquals(result.get(0).getId(), comment.getId());
        assertEquals(result.get(0).getProduct(), comment.getProduct());
        assertEquals(result.get(0).getUser(), comment.getUser());
        assertEquals(result.get(0).getContent(), comment.getContent());
    }

    @Test
    public void deleteCommentTest() {
        commentRepository.delete(comment);
        verify(commentRepository).delete(comment);
    }
}
