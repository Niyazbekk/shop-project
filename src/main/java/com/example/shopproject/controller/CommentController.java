package com.example.shopproject.controller;

import com.example.shopproject.entity.dto.CommentDto;
import com.example.shopproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class CommentController {
    private final CommentService commentService;
    @PostMapping("/comment")
    public CommentDto createComment(@Valid @RequestBody CommentDto comment) {
        log.info("Rest request to create comment");
        return commentService.createComment(comment);
    }
    @GetMapping("/comments")
    public Page<CommentDto> getAllComments(@PageableDefault(value = 5, page = 0) Pageable pageable){
        log.info("Rest request to get all comments by pages with page size = {} and page number = {} " , pageable.getPageSize() , pageable.getPageNumber());
        return commentService.getAllComments(pageable);
    }
    @GetMapping("/comment/{id}")
    public CommentDto getCommentById(@PathVariable Long id) {
        log.info("Rest request to get comment by id = {}",id);
        return commentService.getCommentById(id);
    }
    @PutMapping("/comment")
    public CommentDto updateComment(@Valid @RequestBody CommentDto comment) {
        log.info("Rest request to update comment with userId = {}", comment.getUserId());
        return commentService.updateComment(comment);
    }
    @DeleteMapping("/comment/{id}")
    public void deleteCommentById(@PathVariable Long id) {
        log.info("Rest request to delete comment by id = {}",id);
        commentService.deleteCommentById(id);
    }
}