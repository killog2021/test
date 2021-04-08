package com.github.prgrms.orders;

import com.github.prgrms.errors.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Transactional(readOnly = true)
    public Optional<UOrder> findById(Long orderId) {
        checkNotNull(orderId, "orderId must be provided");

        return orderRepository.findById(orderId);
    }

    @Transactional(readOnly = true)
    public List<UOrder> findAll() {
        return orderRepository.findAll();
    }

    public boolean accept(Long orderId) {

        checkNotNull(orderId, "orderId must be provided");
        UOrder order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Could not found order for " + orderId));
        if (order.getState() == State.REQUESTED) {

            orderRepository.updateState(orderId, State.ACCEPTED.name());
            return true;
        }
        return false;


    }
}
