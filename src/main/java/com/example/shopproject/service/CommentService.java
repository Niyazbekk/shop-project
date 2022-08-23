package com.example.shopproject.service;

import com.example.shopproject.entity.dto.CommentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public interface CommentService {
    CommentDto createComment(CommentDto comment);
    Page<CommentDto> getAllComments(Pageable pageable);
    CommentDto getCommentById(Long id);
    CommentDto updateComment(CommentDto comment);
    void deleteCommentById(Long id);
}
