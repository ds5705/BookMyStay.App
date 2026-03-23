import java.util.*;

class Reservation {
    String reservationId;
    String guestName;
    String roomType;

    Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class BookingHistory {
    private List<Reservation> history = new ArrayList<>();

    public void addReservation(Reservation r) {
        history.add(r);
    }

    public List<Reservation> getAllReservations() {
        return history;
    }
}

class BookingReportService {

    public void displayAllBookings(List<Reservation> list) {
        if (list.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        for (Reservation r : list) {
            System.out.println(r.reservationId + " | " + r.guestName + " | " + r.roomType);
        }
    }

    public void generateSummary(List<Reservation> list) {
        System.out.println("Total Bookings: " + list.size());
    }
}

public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BookingHistory history = new BookingHistory();
        BookingReportService report = new BookingReportService();

        System.out.print("Enter number of bookings: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter Reservation ID: ");
            String id = sc.nextLine();

            System.out.print("Enter Guest Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Room Type: ");
            String room = sc.nextLine();

            history.addReservation(new Reservation(id, name, room));
        }

        System.out.println("\nBooking History:");
        report.displayAllBookings(history.getAllReservations());

        System.out.println("\nReport Summary:");
        report.generateSummary(history.getAllReservations());

        sc.close();
    }
}