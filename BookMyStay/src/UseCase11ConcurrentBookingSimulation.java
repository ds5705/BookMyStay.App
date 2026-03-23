import java.util.*;

class BookingProcessor {

    private Map<String, Integer> inventory = new HashMap<>();

    public BookingProcessor() {
        inventory.put("Deluxe", 1);
    }

    public synchronized void bookRoom(String guestName) {

        int available = inventory.get("Deluxe");

        if (available > 0) {
            System.out.println(guestName + " is booking...");
            inventory.put("Deluxe", available - 1);
            System.out.println(guestName + " successfully booked.");
        } else {
            System.out.println(guestName + " failed. No rooms available.");
        }
    }

    public void displayInventory() {
        System.out.println("Final Inventory: " + inventory);
    }
}

class BookingThread extends Thread {

    private BookingProcessor processor;
    private String guestName;

    public BookingThread(BookingProcessor processor, String guestName) {
        this.processor = processor;
        this.guestName = guestName;
    }

    public void run() {
        processor.bookRoom(guestName);
    }
}

public class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {

        BookingProcessor processor = new BookingProcessor();

        Thread t1 = new BookingThread(processor, "Guest1");
        Thread t2 = new BookingThread(processor, "Guest2");
        Thread t3 = new BookingThread(processor, "Guest3");

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (Exception e) {}

        processor.displayInventory();
    }
}