import java.io.*;
import java.util.*;

class SystemState implements Serializable {
    Map<String, Integer> inventory;
    List<String> bookings;

    SystemState(Map<String, Integer> inventory, List<String> bookings) {
        this.inventory = inventory;
        this.bookings = bookings;
    }
}

class PersistenceService {

    private static final String FILE_NAME = "data.ser";

    public void save(SystemState state) {

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            oos.writeObject(state);
            oos.close();
            System.out.println("State saved successfully.");
        } catch (Exception e) {
            System.out.println("Error saving state.");
        }
    }

    public SystemState load() {

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME));
            SystemState state = (SystemState) ois.readObject();
            ois.close();
            System.out.println("State loaded successfully.");
            return state;
        } catch (Exception e) {
            System.out.println("No previous data found. Starting fresh.");
            return null;
        }
    }
}

public class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        PersistenceService service = new PersistenceService();

        SystemState state = service.load();

        Map<String, Integer> inventory;
        List<String> bookings;

        if (state == null) {
            inventory = new HashMap<>();
            bookings = new ArrayList<>();

            inventory.put("Deluxe", 2);
            inventory.put("Suite", 1);
        } else {
            inventory = state.inventory;
            bookings = state.bookings;
        }

        bookings.add("R" + (bookings.size() + 1));
        inventory.put("Deluxe", inventory.get("Deluxe") - 1);

        System.out.println("Current Inventory: " + inventory);
        System.out.println("Bookings: " + bookings);

        service.save(new SystemState(inventory, bookings));
    }
}