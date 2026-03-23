import java.util.*;

class Booking {
    String reservationId;
    String roomType;
    String roomId;
    boolean active;

    Booking(String reservationId, String roomType, String roomId) {
        this.reservationId = reservationId;
        this.roomType = roomType;
        this.roomId = roomId;
        this.active = true;
    }
}

class CancellationService {

    private Map<String, Booking> bookings = new HashMap<>();
    private Map<String, Integer> inventory = new HashMap<>();
    private Stack<String> rollbackStack = new Stack<>();

    public CancellationService() {
        inventory.put("Deluxe", 1);
        inventory.put("Suite", 1);

        bookings.put("R101", new Booking("R101", "Deluxe", "D1"));
        bookings.put("R102", new Booking("R102", "Suite", "S1"));
    }

    public void cancelBooking(String reservationId) {

        if (!bookings.containsKey(reservationId)) {
            System.out.println("Invalid reservation ID.");
            return;
        }

        Booking b = bookings.get(reservationId);

        if (!b.active) {
            System.out.println("Booking already cancelled.");
            return;
        }

        rollbackStack.push(b.roomId);

        inventory.put(b.roomType, inventory.get(b.roomType) + 1);

        b.active = false;

        System.out.println("Booking cancelled for " + reservationId);
    }

    public void displayStatus() {
        System.out.println("Inventory: " + inventory);
        System.out.println("Rollback Stack: " + rollbackStack);
    }
}

public class UseCase10BookingCancellation {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        CancellationService service = new CancellationService();

        System.out.print("Enter reservation ID to cancel: ");
        String id = sc.nextLine();

        service.cancelBooking(id);

        service.displayStatus();

        sc.close();
    }
}