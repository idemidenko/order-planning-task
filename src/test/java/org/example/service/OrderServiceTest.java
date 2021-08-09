package org.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;
import org.example.model.Customer;
import org.example.model.Order;
import org.example.model.Product;
import org.gavaghan.geodesy.GlobalPosition;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IntegrationTest {

  @Autowired private OrderService orderService;
  @Autowired private CustomerService customerService;
  @Autowired private WarehouseService warehouseService;

  private Customer brestMarket;
  private Customer kaunasShop;

  @BeforeEach
  void init() {
    warehouseService.create(
        "Minsk",
        new GlobalPosition(53.99389190188447, 27.515981742620312, 0),
        new HashMap<>(Map.ofEntries(Map.entry(Product.MILK, 2), Map.entry(Product.SWEETS, 1))));
    warehouseService.create(
        "Vilnius",
        new GlobalPosition(54.72764358687948, 25.198845196442555, 0),
        new HashMap<>(Map.ofEntries(Map.entry(Product.BREAD, 4), Map.entry(Product.SWEETS, 1))));
    warehouseService.create(
        "Warsaw",
        new GlobalPosition(52.31547777191398, 21.110545149557552, 0),
        new HashMap<>(
            Map.ofEntries(
                Map.entry(Product.MILK, 1),
                Map.entry(Product.BREAD, 4),
                Map.entry(Product.SWEETS, 2))));
    warehouseService.printWarehousesState();

    brestMarket = customerService.create(
        "Brest market", new GlobalPosition(52.12597674946001, 23.812549599442836, 0));
    kaunasShop = customerService.create(
        "Kaunas shop", new GlobalPosition(55.04506256536851, 23.742239119033275, 0));
  }

  @Test
  void createOrders() {
    Order order = orderService.createOrder(Product.BREAD, 2, brestMarket);
    assertEquals("Warsaw", order.getWarehouse().getName());

    order = orderService.createOrder(Product.MILK, 2, kaunasShop);
    assertEquals("Minsk", order.getWarehouse().getName());

    order = orderService.createOrder(Product.BREAD, 2, kaunasShop);
    assertEquals("Vilnius", order.getWarehouse().getName());

    RuntimeException thrownException =
        assertThrows(
            RuntimeException.class, () -> orderService.createOrder(Product.SWEETS, 20, kaunasShop));
    assertEquals("No warehouse is found", thrownException.getMessage());

    Customer mogilevTrade = customerService.create(
        "Mogilev trade", new GlobalPosition(54.01759417591646, 30.493638001473677, 0));
    order = orderService.createOrder(Product.SWEETS, 2, mogilevTrade);
    assertEquals("Warsaw", order.getWarehouse().getName());
  }
}
