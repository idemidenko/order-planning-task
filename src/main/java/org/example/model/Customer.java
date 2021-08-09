package org.example.model;

import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gavaghan.geodesy.GlobalPosition;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Customer {

  @EqualsAndHashCode.Include private final UUID id = UUID.randomUUID();
  private String name;
  private GlobalPosition position;

  public Customer(String name, GlobalPosition position) {
    this.name = name;
    this.position = position;
  }
}
