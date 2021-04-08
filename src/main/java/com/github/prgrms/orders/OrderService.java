package com.github.prgrms.orders;

import com.github.prgrms.errors.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    @Transactional
    public boolean accept(Long orderId) {

        checkNotNull(orderId, "orderId must be provided");
        UOrder order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Could not found order for " + orderId));
        if (order.getState().equals(State.REQUESTED.getValue())) {
            order.setState(State.ACCEPTED.getValue());
            orderRepository.update(order);
            return true;
        } else {
            return false;

        }


    }

    public boolean reject(Long orderId) {

        checkNotNull(orderId, "orderId must be provided");
        UOrder order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Could not found order for " + orderId));
        if (order.getState().equals(State.REQUESTED.getValue())) {
            order.setState(State.REJECTED.getValue());
            order.setRejectAt(LocalDateTime.now());

            orderRepository.update(order);
            return true;
        } else {
            return false;

        }
    }

    public Boolean shipping(Long orderId) {

        checkNotNull(orderId, "orderId must be provided");
        UOrder order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Could not found order for " + orderId));
        if (order.getState().equals(State.ACCEPTED.getValue())) {
            order.setState(State.SHIPPING.getValue());

            orderRepository.update(order);
            return true;
        } else {
            return false;

        }
    }

    public Boolean complete(Long orderId) {

        checkNotNull(orderId, "orderId must be provided");
        UOrder order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Could not found order for " + orderId));
        if (order.getState().equals(State.SHIPPING.getValue())) {
            order.setState(State.COMPLETED.getValue());

            orderRepository.update(order);
            return true;
        } else {
            return false;

        }
    }

}
