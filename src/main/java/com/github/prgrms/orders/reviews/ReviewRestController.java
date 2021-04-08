package com.github.prgrms.orders.reviews;

import com.github.prgrms.security.JwtAuthentication;
import com.github.prgrms.utils.ApiUtils.ApiResult;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.github.prgrms.utils.ApiUtils.success;

@RestController
@RequestMapping("api/orders/")
public class ReviewRestController {


    private final ReviewService reviewService;

    public ReviewRestController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @PostMapping(path = "{orderId}/review")
    public ApiResult<Review> review(
            @AuthenticationPrincipal JwtAuthentication authentication, @RequestBody ReviewRequest reviewRequest, @PathVariable Long orderId
    ) {

        return success(reviewService.addReview(authentication, reviewRequest, orderId));
    }
}