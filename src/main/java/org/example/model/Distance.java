package org.example.model;

import lombok.Data;

@Data
public class Distance {

  private Customer customer;
  private Warehouse warehouse;
  private double distance;

  public Distance(Customer customer, Warehouse warehouse) {
    this.customer = customer;
    this.warehouse = warehouse;
  }
}
