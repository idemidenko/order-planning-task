package org.example.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.model.Customer;
import org.example.model.Order;
import org.example.model.Product;
import org.example.model.Warehouse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final WarehouseService warehouseService;

  private final List<Order> ORDERS = new ArrayList<>();

  public Order createOrder(Product product, Integer count, Customer customer) {
    Order order = new Order(product, count, customer);
    Warehouse nearestWarehouse = warehouseService.findNearestWarehouse(order);

    if (nearestWarehouse == null) {
      throw new RuntimeException("No warehouse is found");
    }

    order.setWarehouse(nearestWarehouse);

    warehouseService.reserveProduct(nearestWarehouse, product, count);
    ORDERS.add(order);

    return order;
  }
}
