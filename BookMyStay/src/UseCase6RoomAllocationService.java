import java.util.*;

/**
 * Book My Stay - Hotel Booking Management System
 * Use Case 6: Reservation Confirmation & Room Allocation
 *
 * Demonstrates safe booking confirmation, unique room allocation,
 * and synchronized inventory updates.
 *
 * @author Devansh
 * @version 6.0
 */

class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room", 1);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void decrementRoom(String roomType) {
        int current = inventory.get(roomType);
        inventory.put(roomType, current - 1);
    }

    public void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + " : " + inventory.get(type));
        }
    }
}

class BookingService {

    private Queue<Reservation> requestQueue;
    private RoomInventory inventory;

    private HashMap<String, Set<String>> allocatedRooms;

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
        requestQueue = new LinkedList<>();
        allocatedRooms = new HashMap<>();
    }

    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
        System.out.println("Request added for " + reservation.getGuestName());
    }

    public void processRequests() {

        while (!requestQueue.isEmpty()) {

            Reservation r = requestQueue.poll();
            String type = r.getRoomType();

            if (inventory.getAvailability(type) > 0) {

                String roomId = type.replace(" ", "_") + "_" + UUID.randomUUID().toString().substring(0,5);

                allocatedRooms.putIfAbsent(type, new HashSet<>());
                allocatedRooms.get(type).add(roomId);

                inventory.decrementRoom(type);

                System.out.println("Reservation Confirmed → Guest: " + r.getGuestName()
                        + " | Room Type: " + type
                        + " | Room ID: " + roomId);

            } else {
                System.out.println("Reservation Failed (No Availability) → " + r.getGuestName());
            }
        }
    }

    public void displayAllocations() {

        System.out.println("\nAllocated Rooms:");

        for (String type : allocatedRooms.keySet()) {
            System.out.println(type + " → " + allocatedRooms.get(type));
        }
    }
}

public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay =====");
        System.out.println("Hotel Booking Management System");
        System.out.println("Version: 6.0\n");

        RoomInventory inventory = new RoomInventory();
        BookingService service = new BookingService(inventory);

        service.addRequest(new Reservation("Alice", "Single Room"));
        service.addRequest(new Reservation("Bob", "Single Room"));
        service.addRequest(new Reservation("Charlie", "Single Room"));
        service.addRequest(new Reservation("David", "Suite Room"));

        service.processRequests();

        service.displayAllocations();
        inventory.displayInventory();
    }
}