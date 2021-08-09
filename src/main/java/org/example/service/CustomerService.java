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

  private final List<Customer> CUSTOMERS = new ArrayList<>();

  public Customer create(String name, GlobalPosition position) {
    Customer customer = new Customer(name, position);
    // TODO: implement

    return customer;
  }
}
