# parkingsystem
Low level Design for Parking Lot
### Understanding Parking Lot
A parking lot is a dedicated area that is intended for parking vehicles. Parking lots are
present in every city and suburban area. Shopping malls, stadiums, airports, train stations,
and similar venues often feature a parking lot with a large capacity. A parking lot can spread
across multiple buildings with multiple floors or can be in a large open area.

The parking lot will allow different types of vehicles to be parked:
○ Motorcycles/Scooters
○ Cars/SUVs
○ Buses/Trucks

Each vehicle will occupy a single spot and the spot size will be different for different
vehicles.

The number of spots per vehicle type will be different for different parking lots. For
example
○ Motorcycles/scooters: 100 spots
○ Cars/SUVs: 80 spots
○ Buses/Trucks: 40 spots

When a vehicle is parked, a parking ticket should be generated with the spot number
and the entry date-time.
When a vehicle is unparked, a receipt should be generated with the entry date-time,
exit date-time, and the applicable fees to be paid.

### Problem Statement
Given a parking lot with details about the vehicle types that can be parked, the number of
spots, and the fee model for the parking lot; compute the fees to be paid for the parked
vehicles when the vehicle is unparked.
Example 1: Small motorcycle/scooter parking lot
Spots:
● Motorcycles/scooters: 2 spots
● Cars/SUVs/Buses/Trucks: NA

Example 2: Mall parking lot
Spots:
● Motorcycles/scooters: 100 spots
● Cars/SUVs: 80 spots
● Buses/Trucks: 10 spots

Example 3: Stadium Parking Lot
Spots:
● Motorcycles/scooters: 1000 spots
● Cars/SUVs: 1500 spots

#### Expected Solution
Scenarios: 
● Motorcycle parked for 3 hours and 30 mins. Fees: 40
● Car parked for 6 hours and 1 min. Fees: 140
● Truck parked for 1 hour and 59 mins. Fees: 100

