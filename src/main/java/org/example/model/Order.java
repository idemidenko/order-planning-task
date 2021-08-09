package org.example.model;

import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order {

  @EqualsAndHashCode.Include
  private final UUID id = UUID.randomUUID();
  private Product product;
  private Integer count;
  private Customer customer;
  private Warehouse warehouse;

  public Order(Product product, Integer count, Customer customer) {
    this.product = product;
    this.count = count;
    this.customer = customer;
  }
}
