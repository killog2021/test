package com.github.prgrms.orders;

import com.github.prgrms.errors.NotFoundException;
import com.github.prgrms.utils.ApiUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.github.prgrms.utils.ApiUtils.success;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("api/orders")
public class OrderRestController {
    // TODO findAll, findById, accept, reject, shipping, complete 메소드 구현이 필요합니다.

    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping(path = "{id}")
    public ApiUtils.ApiResult<OrderDto> findById(@PathVariable Long id) {
        return success(orderService.findById(id).map(OrderDto::new)
                .orElseThrow(() -> new NotFoundException("Could not found order for " + id)));
    }


    @GetMapping
    public ApiUtils.ApiResult<List<OrderDto>> findAll() {
        return success(orderService.findAll().stream()
                .map(OrderDto::new)
                .collect(toList()));
    }
}