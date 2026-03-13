import java.util.HashMap;

/**
 * Book My Stay - Hotel Booking Management System
 * Use Case 3: Centralized Room Inventory Management
 *
 * Demonstrates centralized room availability using HashMap.
 *
 * @author Devansh
 * @version 3.1
 */

class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 10);
        inventory.put("Double Room", 6);
        inventory.put("Suite Room", 3);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void updateAvailability(String roomType, int change) {
        int current = inventory.getOrDefault(roomType, 0);
        inventory.put(roomType, current + change);
    }

    public void displayInventory() {

        System.out.println("Current Room Inventory:");

        for (String roomType : inventory.keySet()) {
            System.out.println(roomType + " : " + inventory.get(roomType));
        }
    }
}

public class UseCase3InventorySetup {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay =====");
        System.out.println("Hotel Booking Management System");
        System.out.println("Version: 3.1\n");

        RoomInventory inventory = new RoomInventory();

        inventory.displayInventory();

        System.out.println("\nChecking availability for Single Room:");
        System.out.println("Available: " + inventory.getAvailability("Single Room"));

        System.out.println("\nUpdating availability (1 Single Room booked)");
        inventory.updateAvailability("Single Room", -1);

        System.out.println("\nUpdated Inventory:");
        inventory.displayInventory();
    }
}