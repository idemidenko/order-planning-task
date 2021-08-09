package org.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

  public Warehouse create(
      String name, GlobalPosition position, Map<Product, Integer> productCount) {
    Warehouse warehouse = new Warehouse(name, position, productCount);
    WAREHOUSES.add(warehouse);

    return warehouse;
  }

  public Warehouse findNearestWarehouse(Order order) {
    // TODO: implement
    return null;
  }

  public List<Warehouse> getAll() {
    return WAREHOUSES;
  }

  public void reserveProduct(Warehouse warehouse, Product product, int count) {
    // TODO: implement

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
