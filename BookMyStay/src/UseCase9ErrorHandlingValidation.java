import java.util.*;

class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

class BookingService {

    private Map<String, Integer> inventory = new HashMap<>();

    public BookingService() {
        inventory.put("Deluxe", 2);
        inventory.put("Suite", 1);
    }

    public void bookRoom(String roomType) throws InvalidBookingException {

        if (!inventory.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type.");
        }

        int available = inventory.get(roomType);

        if (available <= 0) {
            throw new InvalidBookingException("No rooms available for " + roomType);
        }

        inventory.put(roomType, available - 1);

        System.out.println("Booking confirmed for " + roomType);
    }

    public void displayInventory() {
        System.out.println("Current Inventory: " + inventory);
    }
}

public class UseCase9ErrorHandlingValidation {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BookingService service = new BookingService();

        try {
            System.out.print("Enter room type: ");
            String roomType = sc.nextLine();

            service.bookRoom(roomType);

        } catch (InvalidBookingException e) {
            System.out.println("Error: " + e.getMessage());
        }

        service.displayInventory();

        sc.close();
    }
}