package com.github.prgrms.orders;

import com.github.prgrms.errors.NotFoundException;
import com.github.prgrms.utils.ApiUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.github.prgrms.utils.ApiUtils.success;

@RestController
@RequestMapping("api/orders")
public class OrderRestController {
    // TODO findAll, findById, accept, reject, shipping, complete 메소드 구현이 필요합니다.

    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping(path = "{id}")
    public ApiUtils.ApiResult<UOrder> findById(@PathVariable Long id) {
        return success(orderService.findById(id).orElseThrow(() -> new NotFoundException("Could not found order for " + id)));
    }


    @GetMapping
    public ApiUtils.ApiResult<List<UOrder>> findAll() {
        return success(orderService.findAll());
    }

    @PatchMapping(path = "{id}/accept")
    public ApiUtils.ApiResult<Boolean> accept(@PathVariable Long id) {

        return success(orderService.accept(id));
    }


    @PatchMapping(path = "{id}/reject")
    public ApiUtils.ApiResult<Boolean> reject(@PathVariable Long id) {

        return success(orderService.reject(id));
    }
}