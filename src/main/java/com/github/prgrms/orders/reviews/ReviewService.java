package com.github.prgrms.orders.reviews;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ReviewService {


    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Transactional
    public Review addReview(Long userSeq, Long productSeq, String dontent) {

        Review review = new Review.Builder().userSeq(userSeq).content(dontent).productSeq(productSeq).build();
        reviewRepository.save(review);
        return review;
    }


}