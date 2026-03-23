import java.util.*;

class Service {
    String name;
    double cost;

    Service(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }
}

class AddOnServiceManager {

    private Map<String, List<Service>> serviceMap = new HashMap<>();

    public void addService(String reservationId, Service service) {
        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);
    }

    public double calculateTotalCost(String reservationId) {
        double total = 0;
        List<Service> services = serviceMap.get(reservationId);

        if (services != null) {
            for (Service s : services) {
                total += s.cost;
            }
        }
        return total;
    }

    public void displayServices(String reservationId) {
        List<Service> services = serviceMap.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No add-on services selected.");
            return;
        }

        for (Service s : services) {
            System.out.println(s.name + " - " + s.cost);
        }
    }
}

public class
UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AddOnServiceManager manager = new AddOnServiceManager();

        System.out.print("Enter Reservation ID: ");
        String reservationId = sc.nextLine();

        System.out.print("How many services to add: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter service name: ");
            String name = sc.nextLine();

            System.out.print("Enter service cost: ");
            double cost = sc.nextDouble();
            sc.nextLine();

            manager.addService(reservationId, new Service(name, cost));
        }

        System.out.println("\nSelected Services:");
        manager.displayServices(reservationId);

        double total = manager.calculateTotalCost(reservationId);

        System.out.println("Total Add-On Cost: " + total);

        sc.close();
    }
}