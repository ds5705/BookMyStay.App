/**
 * Book My Stay - Hotel Booking Management System
 * Use Case 2: Basic Room Types & Static Availability
 *
 * Demonstrates abstraction, inheritance, and polymorphism
 * using predefined room types.
 *
 * @author Devansh
 * @version 2.1
 */

abstract class Room {

    protected String roomType;
    protected int beds;
    protected int size;
    protected double price;

    public Room(String roomType, int beds, int size, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public void displayDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sq ft");
        System.out.println("Price per night: $" + price);
    }
}

class SingleRoom extends Room {

    public SingleRoom() {
        super("Single Room", 1, 200, 80);
    }
}

class DoubleRoom extends Room {

    public DoubleRoom() {
        super("Double Room", 2, 350, 120);
    }
}

class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 3, 500, 250);
    }
}

public class UseCase2RoomInitialization {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay =====");
        System.out.println("Hotel Booking Management System");
        System.out.println("Version: 2.1\n");

        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        int singleAvailability = 10;
        int doubleAvailability = 6;
        int suiteAvailability = 3;

        System.out.println("Single Room Details:");
        single.displayDetails();
        System.out.println("Available Rooms: " + singleAvailability);
        System.out.println();

        System.out.println("Double Room Details:");
        doubleRoom.displayDetails();
        System.out.println("Available Rooms: " + doubleAvailability);
        System.out.println();

        System.out.println("Suite Room Details:");
        suite.displayDetails();
        System.out.println("Available Rooms: " + suiteAvailability);
    }
}