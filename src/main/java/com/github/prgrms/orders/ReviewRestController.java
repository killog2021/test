package com.github.prgrms.orders;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.prgrms.products.ProductService;
import com.github.prgrms.users.UserService;

@RestController
@RequestMapping("api/orders/")
public class ReviewRestController {


  private final ProductService productService;
  private final UserService userService;
  private final ReviewService reviewService;

  public ReviewRestController(ProductService productService, UserService userService, ReviewService reviewService) {
    this.productService = productService;
    this.userService = userService;
    this.reviewService=reviewService;
  }


  @PostMapping(path = "{productId}/review")
  public ApiResult<UserDto> review(
    @AuthenticationPrincipal JwtAuthentication authentication, ReviewRequest reviewRequest,@PathVariable Long id
  ) {
      userService.findById(authentication.id)
        .map(UserDto::new)
        .orElseThrow(() -> new NotFoundException("Could nof found user for " + authentication.id));

        /*productService.findById(productId)
        .map(ProductDto::new)
        .orElseThrow(() -> new NotFoundException("Could nof found product for " + productId));   
      */
        return reviewService.addReview(authentication.id, productId ,reviewRequest);
  }
}