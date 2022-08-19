package com.example.shopproject.service;

import com.example.shopproject.entity.Comment;
import com.example.shopproject.entity.dto.CommentDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    CommentDto createComment(CommentDto comment);
    List<Comment> getAllComments(Pageable pageable);
    CommentDto getCommentById(Long id);
    CommentDto updateComment(CommentDto comment);
    void deleteCommentById(Long id);
}
