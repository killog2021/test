package com.github.prgrms.orders;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository {

    public Optional<UOrder> findById(Long orderId);

    public List<UOrder> findAll();

    public void update(UOrder order);
}
