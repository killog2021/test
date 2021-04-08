package com.github.prgrms.reviews;

import com.github.prgrms.errors.NotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static java.time.LocalDateTime.now;
import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class ReviewService {


  private final ReviewRepository reviewRepository;

  public ReviewService(ReviewRepository reviewRepository) {
    this.reviewRepository = reviewRepository;
  }

  @Transactional
  public ReviewDto addReview(Long userSeq, Long productSeq, ReviewRequest reviewRequest) {

    
    reviewRepository.save(userSeq, productSeq, reviewRequest.getContent(),now());
    return review;
  }

  
}