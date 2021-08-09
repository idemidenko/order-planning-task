package org.example.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.Customer;
import org.example.model.Distance;
import org.example.model.Order;
import org.example.model.Product;
import org.example.model.Warehouse;
import org.gavaghan.geodesy.GlobalPosition;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class WarehouseService {

  private final List<Warehouse> WAREHOUSES = new ArrayList<>();
  private final DistanceService distanceService;

  public Warehouse create(
      String name, GlobalPosition position, Map<Product, Integer> productCount) {
    Warehouse warehouse = new Warehouse(name, position, productCount);
    WAREHOUSES.add(warehouse);

    return warehouse;
  }

  public Warehouse findNearestWarehouse(Order order) {
    Product product = order.getProduct();
    Integer count = order.getCount();
    Customer customer = order.getCustomer();

    return WAREHOUSES.stream()
        .filter(warehouse -> warehouse.getProductCount().getOrDefault(product, 0) >= count)
        .min(
            Comparator.comparingDouble(
                warehouse ->
                    distanceService
                        .findDistance(warehouse, customer)
                        .map(Distance::getDistance)
                        .orElseThrow()))
        .orElse(null);
  }

  public List<Warehouse> getAll() {
    return WAREHOUSES;
  }

  public void reserveProduct(Warehouse warehouse, Product product, int count) {
    Integer availableCount = warehouse.getProductCount().get(product);

    if (availableCount == null) {
      throw new IllegalArgumentException(
          String.format("No product (%s) found in warehouse (%s)", product, warehouse));
    }

    int leftCount = availableCount - count;
    if (leftCount < 0) {
      throw new IllegalArgumentException(
          String.format(
              "Product (%s) available count (%d) in warehouse (%s) is less than reserved count (%d)",
              product, availableCount, warehouse, count));
    }

    warehouse.getProductCount().put(product, leftCount);
    printWarehousesState();
  }

  public void printWarehousesState() {
    String message =
        WAREHOUSES.stream()
            .map(w -> w.getName() + ": " + w.getProductCount())
            .collect(Collectors.joining(System.lineSeparator()));
    log.info("WarehousesState: {}{}", System.lineSeparator(), message);
  }
}
