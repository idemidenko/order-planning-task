package org.example.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.model.Customer;
import org.gavaghan.geodesy.GlobalPosition;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

  private final DistanceService distanceService;
  private final WarehouseService warehouseService;

  private final List<Customer> CUSTOMERS = new ArrayList<>();

  public Customer create(String name, GlobalPosition position) {
    Customer customer = new Customer(name, position);
    warehouseService.getAll().forEach(warehouse -> distanceService.save(warehouse, customer));
    CUSTOMERS.add(customer);

    return customer;
  }
}
