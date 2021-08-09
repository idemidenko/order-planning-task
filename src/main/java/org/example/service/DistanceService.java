package org.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.example.model.Customer;
import org.example.model.Distance;
import org.example.model.Warehouse;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.springframework.stereotype.Service;

@Service
public class DistanceService {

  private final GeodeticCalculator calculator = new GeodeticCalculator();
  private final List<Distance> distances = new ArrayList<>();

  public Distance save(Warehouse warehouse, Customer customer) {
    Distance distance = new Distance(customer, warehouse);
    Distance modifiedDistance =
        distances.stream()
            .filter(d -> Objects.equals(d, distance))
            .findAny()
            .orElseGet(
                () -> {
                  distances.add(distance);
                  return distance;
                });

    double metricDistance =
        calculator
            .calculateGeodeticCurve(
                Ellipsoid.WGS84, warehouse.getPosition(), customer.getPosition())
            .getEllipsoidalDistance();
    modifiedDistance.setDistance(metricDistance);

    return modifiedDistance;
  }

  public Optional<Distance> findDistance(Warehouse warehouse, Customer customer) {
    return distances.stream()
        .filter(
            distance ->
                Objects.equals(distance.getWarehouse(), warehouse)
                    && Objects.equals(distance.getCustomer(), customer))
        .findAny();
  }
}
