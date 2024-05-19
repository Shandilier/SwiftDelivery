# SwiftDelivery

1. Entry point (Controller)
   1. AdminController- contains an api to add the connection between two nodes/junctions.
   2. DeliveryController- contains an api to get the shortest time to complete delivery.
2. Service
   1. It contains a backtracking based algorithm to traverse the dense graph and get the minimum travel time.
   2. This layer can be extended to implement similar algorithm
3. Model 
   1. Each junction is represented by a node (PickUpNode for customer and DeliveryNode for restaurant).
   2. Each node is mapped to list of neighbour nodes
      1. Each node contains the node type(Restaurant or Customer), weight/time taken to reach this node from previous juntion/node and the node id.
   3. DeliveryGraphWithMap contains function to connect two junctions based on the api request
4. Store
   1. It stores all the restaurants and also the shortest time during a given path.
5. Exception
   1. Custom Exception class to check if the admin request to connect junctions/nodes was successful or not.
6. Aspect
    1. Keeps track of all the controller class being accessed.
7. We can add a new api to create bulk connections in a single request.

ALGORITHM

- For each path, we check the total orders that are completed.
- It ensures that the order is marked complete only when source i.e the restaurant is visited first and the corresponding customer is visited after that.
- There might be a case where the delivery agent might have to travel from a path crossing one of the customer more than once but deivering it in the subsequent visit as the agent didn't pick the order for that customer initially.

