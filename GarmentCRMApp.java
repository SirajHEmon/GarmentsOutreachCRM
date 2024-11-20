import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GarmentCRMApp {

    static class Buyer {
        private static int counter = 1;
        private int id;
        private String name;
        private String company;
        private String email;
        private String phone;

        public Buyer(String name, String company, String email, String phone) {
            this.id = counter++;
            this.name = name;
            this.company = company;
            this.email = email;
            this.phone = phone;
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public String getCompany() { return company; }
        public String getEmail() { return email; }
        public String getPhone() { return phone; }

        @Override
        public String toString() {
            return "Buyer ID: " + id +
                   "\nName: " + name +
                   "\nCompany: " + company +
                   "\nEmail: " + email +
                   "\nPhone: " + phone + "\n";
        }
    }

    static class Outreach {
        private static int counter = 1;
        private int id;
        private int buyerId;
        private String date;
        private String status;
        private String notes;

        public Outreach(int buyerId, String date, String status, String notes) {
            this.id = counter++;
            this.buyerId = buyerId;
            this.date = date;
            this.status = status;
            this.notes = notes;
        }

        public int getId() { return id; }
        public int getBuyerId() { return buyerId; }
        public String getDate() { return date; }
        public String getStatus() { return status; }
        public String getNotes() { return notes; }

        @Override
        public String toString() {
            return "Outreach ID: " + id +
                   "\nBuyer ID: " + buyerId +
                   "\nDate: " + date +
                   "\nStatus: " + status +
                   "\nNotes: " + notes + "\n";
        }
    }


    private List<Buyer> buyers = new ArrayList<>();
    private List<Outreach> outreaches = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);


    public void start() {
        while (true) {
            System.out.println("===== Garment CRM Management =====");
            System.out.println("1. Buyer Management");
            System.out.println("2. Outreach Management");
            System.out.println("3. CRM Dashboard");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    buyerManagementMenu();
                    break;
                case "2":
                    outreachManagementMenu();
                    break;
                case "3":
                    displayDashboard();
                    break;
                case "4":
                    System.out.println("Exiting... Thank you!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.\n");
            }
        }
    }

    private void buyerManagementMenu() {
        while (true) {
            System.out.println("\n--- Buyer Management ---");
            System.out.println("1. Add Buyer");
            System.out.println("2. View All Buyers");
            System.out.println("3. Search Buyer by ID");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addBuyer();
                    break;
                case "2":
                    viewAllBuyers();
                    break;
                case "3":
                    searchBuyerById();
                    break;
                case "4":
                    System.out.println();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.\n");
            }
        }
    }

    private void addBuyer() {
        System.out.print("Enter Buyer Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Company Name: ");
        String company = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();

        Buyer buyer = new Buyer(name, company, email, phone);
        buyers.add(buyer);

        System.out.println("Buyer added successfully!\n");
    }

    private void viewAllBuyers() {
        if (buyers.isEmpty()) {
            System.out.println("No buyers found.\n");
            return;
        }

        System.out.println("\n--- List of Buyers ---");
        for (Buyer buyer : buyers) {
            System.out.println(buyer);
        }
    }

    private void searchBuyerById() {
        System.out.print("Enter Buyer ID to search: ");
        String input = scanner.nextLine();
        try {
            int id = Integer.parseInt(input);
            Buyer buyer = findBuyerById(id);
            if (buyer != null) {
                System.out.println("\nBuyer Details:");
                System.out.println(buyer);
            } else {
                System.out.println("Buyer with ID " + id + " not found.\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a numeric value.\n");
        }
    }

    // Find Buyer by ID
    private Buyer findBuyerById(int id) {
        for (Buyer buyer : buyers) {
            if (buyer.getId() == id) {
                return buyer;
            }
        }
        return null;
    }

    private void outreachManagementMenu() {
        while (true) {
            System.out.println("\n--- Outreach Management ---");
            System.out.println("1. Add Outreach Record");
            System.out.println("2. View All Outreach Records");
            System.out.println("3. Back to Main Menu");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addOutreach();
                    break;
                case "2":
                    viewAllOutreaches();
                    break;
                case "3":
                    System.out.println();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.\n");
            }
        }
    }

    private void addOutreach() {
        if (buyers.isEmpty()) {
            System.out.println("No buyers available. Please add buyers first.\n");
            return;
        }

        System.out.print("Enter Buyer ID for Outreach: ");
        String input = scanner.nextLine();
        try {
            int buyerId = Integer.parseInt(input);
            Buyer buyer = findBuyerById(buyerId);
            if (buyer == null) {
                System.out.println("Buyer with ID " + buyerId + " not found.\n");
                return;
            }

            System.out.print("Enter Outreach Date (YYYY-MM-DD): ");
            String date = scanner.nextLine();

            System.out.print("Enter Outreach Status (Pending/Interested/Rejected): ");
            String status = scanner.nextLine();

            System.out.print("Enter Notes: ");
            String notes = scanner.nextLine();

            Outreach outreach = new Outreach(buyerId, date, status, notes);
            outreaches.add(outreach);

            System.out.println("Outreach record added successfully!\n");
        } catch (NumberFormatException e) {
            System.out.println("Invalid Buyer ID format. Please enter a numeric value.\n");
        }
    }

    private void viewAllOutreaches() {
        if (outreaches.isEmpty()) {
            System.out.println("No outreach records found.\n");
            return;
        }

        System.out.println("\n--- List of Outreach Records ---");
        for (Outreach outreach : outreaches) {
            System.out.println(outreach);
        }
    }
    private void displayDashboard() {
        System.out.println("\n=== CRM Dashboard ===");
        System.out.println("Total Buyers: " + buyers.size());
        System.out.println("Total Outreach Records: " + outreaches.size());

        System.out.println("\n--- Buyers with Most Outreach ---");
        if (buyers.isEmpty()) {
            System.out.println("No buyers available.\n");
            return;
        }

        int maxOutreach = 0;
        List<Buyer> topBuyers = new ArrayList<>();
        for (Buyer buyer : buyers) {
            int count = 0;
            for (Outreach outreach : outreaches) {
                if (outreach.getBuyerId() == buyer.getId()) {
                    count++;
                }
            }
            if (count > maxOutreach) {
                maxOutreach = count;
                topBuyers.clear();
                topBuyers.add(buyer);
            } else if (count == maxOutreach && count > 0) {
                topBuyers.add(buyer);
            }
        }

        if (maxOutreach == 0) {
            System.out.println("No outreach records available.\n");
        } else {
            System.out.println("Maximum Outreach Count: " + maxOutreach);
            for (Buyer buyer : topBuyers) {
                System.out.println(buyer);
            }
        }
    }

    public static void main(String[] args) {
        GarmentCRMApp app = new GarmentCRMApp();
        app.start();
    }
}

// Add this method in the `GarmentCRMApp` class
private void deleteBuyer() {
    System.out.print("Enter Buyer ID to delete: ");
    String input = scanner.nextLine();
    try {
        int id = Integer.parseInt(input);
        Buyer buyer = findBuyerById(id);
        if (buyer == null) {
            System.out.println("Buyer with ID " + id + " not found.\n");
            return;
        }

        // Remove buyer and associated outreach records
        buyers.remove(buyer);
        outreaches.removeIf(outreach -> outreach.getBuyerId() == id);

        System.out.println("Buyer and associated outreach records deleted successfully!\n");
    } catch (NumberFormatException e) {
        System.out.println("Invalid ID format. Please enter a numeric value.\n");
    }
}

private void buyerManagementMenu() {
    while (true) {
        System.out.println("\n--- Buyer Management ---");
        System.out.println("1. Add Buyer");
        System.out.println("2. View All Buyers");
        System.out.println("3. Search Buyer by ID");
        System.out.println("4. Update Buyer Information");
        System.out.println("5. Delete Buyer"); // New Option
        System.out.println("6. Back to Main Menu");
        System.out.print("Choose an option: ");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                addBuyer();
                break;
            case "2":
                viewAllBuyers();
                break;
            case "3":
                searchBuyerById();
                break;
            case "4":
                updateBuyer();
                break;
            case "5":
                deleteBuyer(); // Call the new method
                break;
            case "6":
                System.out.println();
                return;
            default:
                System.out.println("Invalid option. Please try again.\n");
        }
    }
}