# Order planning

A Manufacturing company has various warehouses in multiple cities. When an order for a
product is placed by their customers, the company wants to make sure the order is picked up
from the warehouse that is closest to the customer's location and containing the product.

## Task

Build APIs to:
 - add a new customer,
 - place a new order.

The order placement API in the response should indicate from which warehouse the product
would be delivered and the distance that should be covered.

### Assumptions

Assume that the distance between the customers location and warehouse is provided
when customer is added: the system would compute it using the data that is provided during
setup. The format of the data can be anything as per the solution.
