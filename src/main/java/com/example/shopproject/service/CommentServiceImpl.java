package com.example.shopproject.service;

import com.example.shopproject.entity.Comment;
import com.example.shopproject.entity.dto.CommentDto;
import com.example.shopproject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;


    @Override
    public CommentDto createComment(CommentDto comment) {
        commentRepository.save(modelMapper.map(comment, Comment.class));
        return comment;
    }

    @Override
    public Page<Comment> getAllComments(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    @Override
    public CommentDto getCommentById(Long id) {
        return modelMapper.map(commentRepository.getReferenceById(id), CommentDto.class);
    }

    @Override
    public CommentDto updateComment(CommentDto comment) {
        commentRepository.save(modelMapper.map(comment, Comment.class));
        return comment;
    }

    @Override
    public void deleteCommentById(Long id) {
        commentRepository.deleteById(id);
    }
}
