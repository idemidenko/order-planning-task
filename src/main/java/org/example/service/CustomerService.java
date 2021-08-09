package org.example.service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
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

  @PostConstruct
  private void init() {
    create("Brest market", new GlobalPosition(52.12597674946001, 23.812549599442836, 0));
    create("Kaunas shop", new GlobalPosition(55.04506256536851, 23.742239119033275, 0));
    create("Mogilev trade", new GlobalPosition(54.01759417591646, 30.493638001473677, 0));
  }

  public Customer create(String name, GlobalPosition position) {
    Customer customer = new Customer(name, position);
    warehouseService.getAll().forEach(warehouse -> distanceService.save(warehouse, customer));
    CUSTOMERS.add(customer);

    return customer;
  }
}
