package org.example.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gavaghan.geodesy.GlobalPosition;

@Data
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Warehouse {

  @EqualsAndHashCode.Include private final UUID id = UUID.randomUUID();
  private String name;
  private GlobalPosition position;
  private Map<Product, Integer> productCount;
}
