import java.util.HashMap;

/**
 * Book My Stay - Hotel Booking Management System
 * Use Case 4: Room Search & Availability Check
 *
 * Demonstrates read-only access to centralized inventory.
 *
 * @author Devansh
 * @version 4.0
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

    public String getRoomType() {
        return roomType;
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

class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 10);
        inventory.put("Double Room", 6);
        inventory.put("Suite Room", 0);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public HashMap<String, Integer> getAllRooms() {
        return inventory;
    }
}

public class UseCase4RoomSearch {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay =====");
        System.out.println("Hotel Booking Management System");
        System.out.println("Version: 4.0\n");

        RoomInventory inventory = new RoomInventory();

        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        Room[] rooms = {single, doubleRoom, suite};

        System.out.println("Available Rooms:\n");

        for (Room room : rooms) {

            int availability = inventory.getAvailability(room.getRoomType());

            if (availability > 0) {
                room.displayDetails();
                System.out.println("Available: " + availability);
                System.out.println();
            }
        }
    }
}