package com.github.prgrms.orders.reviews;

import com.github.prgrms.errors.NotFoundException;
import com.github.prgrms.orders.OrderService;
import com.github.prgrms.orders.UOrder;
import com.github.prgrms.security.JwtAuthentication;
import com.github.prgrms.users.User;
import com.github.prgrms.users.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ReviewService {

    private final OrderService orderService;
    private final UserService userService;
    private final ReviewRepository reviewRepository;

    public ReviewService(OrderService orderService, UserService userService, ReviewRepository reviewRepository) {
        this.orderService = orderService;
        this.userService = userService;
        this.reviewRepository = reviewRepository;
    }

    @Transactional
    public Review addReview(JwtAuthentication authentication, ReviewRequest reviewRequest, Long orderId) {
        User user = userService.findById(authentication.id)
                .orElseThrow(() -> new NotFoundException("Could nof found user for " + authentication.id));
        Long userSeq = user.getSeq();

        UOrder order = orderService.findById(orderId).orElseThrow(() -> new NotFoundException("order not fount"));
        Long productSeq = order.getProductSeq();
        Review review = new Review.Builder().userSeq(userSeq).content(reviewRequest.getContent()).productSeq(productSeq).build();
        reviewRepository.save(review);
        return review;
    }


}