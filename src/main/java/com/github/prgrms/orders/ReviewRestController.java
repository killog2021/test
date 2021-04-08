package com.github.prgrms.orders;

import com.github.prgrms.errors.NotFoundException;
import com.github.prgrms.products.ProductService;
import com.github.prgrms.security.JwtAuthentication;
import com.github.prgrms.users.UserDto;
import com.github.prgrms.users.UserService;
import com.github.prgrms.utils.ApiUtils.ApiResult;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.github.prgrms.utils.ApiUtils.success;

@RestController
@RequestMapping("api/orders/")
public class ReviewRestController {


    private final ProductService productService;
    private final UserService userService;
    private final ReviewService reviewService;

    public ReviewRestController(ProductService productService, UserService userService, ReviewService reviewService) {
        this.productService = productService;
        this.userService = userService;
        this.reviewService = reviewService;
    }


    @PostMapping(path = "{productId}/review")
    public ApiResult<Review> review(
            @AuthenticationPrincipal JwtAuthentication authentication, @RequestBody ReviewRequest review, @PathVariable Long productId
    ) {

        userService.findById(authentication.id)
                .map(UserDto::new)
                .orElseThrow(() -> new NotFoundException("Could nof found user for " + authentication.id));

        /*productService.findById(productId)
        .map(ProductDto::new)
        .orElseThrow(() -> new NotFoundException("Could nof found product for " + productId));   
      */
        return success(reviewService.addReview(authentication.id, productId, review.getContent()));
    }
}