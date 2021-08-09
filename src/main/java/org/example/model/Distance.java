package org.example.model;

import lombok.Data;

@Data
public class Distance {

  private Customer customer;
  private Warehouse warehouse;
  private double metricDistance;

  public Distance(Customer customer, Warehouse warehouse) {
    this.customer = customer;
    this.warehouse = warehouse;
  }
}
