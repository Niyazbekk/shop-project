package com.example.shopproject.controller;

import com.example.shopproject.entity.Comment;
import com.example.shopproject.entity.dto.CommentDto;
import com.example.shopproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CommentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);
    private final CommentService CommentService;

    @PostMapping("/comment")
    public CommentDto createComment(@Valid @RequestBody CommentDto Comment) {
        LOGGER.info("create Comment");
        return CommentService.createComment(Comment);
    }

    @GetMapping("/comments")
    public List<Comment> getAllComments(@RequestParam(defaultValue = "0") int page){
        LOGGER.info("get all Comments");
        PageRequest pageRequest = PageRequest.of(page, 3);
        return CommentService.getAllComments(pageRequest);
    }

    @GetMapping("/comment/{id}")
    public CommentDto getCommentById(@PathVariable Long id) {
        LOGGER.info("get Comment by id");
        return CommentService.getCommentById(id);
    }

    @PutMapping("/comment")
    public CommentDto updateComment(@Valid @RequestBody CommentDto Comment) {
        LOGGER.info("update");
        return CommentService.updateComment(Comment);
    }

    @DeleteMapping("/comment/{id}")
    public void deleteCommentById(@PathVariable Long id) {
        LOGGER.info("delete");
        CommentService.deleteCommentById(id);
    }
}
