package com.bhavesh.linkedInProject.postsService.service;


import com.bhavesh.linkedInProject.postsService.entity.Post;
import com.bhavesh.linkedInProject.postsService.entity.PostLike;
import com.bhavesh.linkedInProject.postsService.exception.ResourceNotFoundException;
import com.bhavesh.linkedInProject.postsService.repository.PostLikeRepository;
import com.bhavesh.linkedInProject.postsService.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import com.bhavesh.linkedInProject.postsService.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;


    @Transactional
    public void likePost(Long postId) {
        Long userId = 1L; //hardcoding for now

        log.info("User with ID: {} liking the post with ID: {}", userId, postId);

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post not found with ID: " + postId)
        );

        boolean hasAlreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId, postId);

        if(hasAlreadyLiked) throw new BadRequestException("You cannot like the post again");


        PostLike postLike = new PostLike();
        postLike.setUserId(userId);
        postLike.setPostId(postId);
        postLikeRepository.save(postLike);
        
        //TODO: send notification to owner of the post
    }


    @Transactional
    public void unlikePost(Long postId) {

        Long userId = 1L; //hardcoding for now

        log.info("User with ID: {} unliking the post with ID: {}", userId, postId);

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post not found with ID: " + postId)
        );

        boolean hasAlreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId, postId);

        if(!hasAlreadyLiked) throw new BadRequestException("You cannot unlike the post that you have not liked");

        postLikeRepository.deleteByUserIdAndPostId(userId, postId);

        //TODO: send notification to owner of the post

    }
}
