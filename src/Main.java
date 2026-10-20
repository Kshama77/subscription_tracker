import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Subscription> subscriptions = new ArrayList<>();

    public static void main(String[] args) {
        addSampleSubscriptions();

        boolean running = true;
        while (running) {
            showMenu();
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    viewSubscriptions();
                    break;
                case "2":
                    addSubscription();
                    break;
                case "3":
                    viewUpcomingRenewals();
                    break;
                case "4":
                    showMonthlyEstimate();
                    break;
                case "5":
                    removeSubscription();
                    break;
                case "0":
                    running = false;
                    System.out.println("Thanks for using SubscriptionTracker!");
                    break;
                default:
                    System.out.println("Please enter a valid option.");
            }
        }
        scanner.close();
    }

    private static void showMenu() {
        System.out.println("\\n==================================");
        System.out.println("       SUBSCRIPTION TRACKER");
        System.out.println("==================================");
        System.out.println("1. View all subscriptions");
        System.out.println("2. Add a subscription");
        System.out.println("3. View upcoming renewals (next 30 days)");
        System.out.println("4. Estimate monthly subscription cost");
        System.out.println("5. Remove a subscription");
        System.out.println("0. Exit");
        System.out.println("----------------------------------");
    }

    private static void addSampleSubscriptions() {
        subscriptions.add(new Subscription("Netflix", 15.99, "Monthly", LocalDate.now().plusDays(5)));
        subscriptions.add(new Subscription("Spotify", 10.99, "Monthly", LocalDate.now().plusDays(12)));
        subscriptions.add(new Subscription("Cloud Storage", 29.99, "Yearly", LocalDate.now().plusDays(22)));
    }

    private static void viewSubscriptions() {
        if (subscriptions.isEmpty()) {
            System.out.println("No subscriptions found. Add one to get started.");
            return;
        }

        System.out.println("\\nNAME                 COST      BILLING      RENEWAL DATE");
        System.out.println("---------------------------------------------------------");
        for (int i = 0; i < subscriptions.size(); i++) {
            System.out.println((i + 1) + ". " + subscriptions.get(i));
        }
    }

    private static void addSubscription() {
        System.out.print("Subscription name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        double cost;
        try {
            System.out.print("Cost ($): ");
            cost = Double.parseDouble(scanner.nextLine().trim());
            if (cost < 0) {
                System.out.println("Cost cannot be negative.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number for cost.");
            return;
        }

        System.out.print("Billing cycle (Monthly/Yearly/Weekly): ");
        String cycle = scanner.nextLine().trim();
        if (cycle.isEmpty()) {
            System.out.println("Billing cycle cannot be empty.");
            return;
        }

        LocalDate renewalDate;
        try {
            System.out.print("Next renewal date (YYYY-MM-DD): ");
            renewalDate = LocalDate.parse(scanner.nextLine().trim());
        } catch (Exception e) {
            System.out.println("Invalid date. Use YYYY-MM-DD, for example 2026-12-01.");
            return;
        }

        subscriptions.add(new Subscription(name, cost, cycle, renewalDate));
        System.out.println("Subscription added successfully!");
    }

    private static void viewUpcomingRenewals() {
        LocalDate today = LocalDate.now();
        LocalDate limit = today.plusDays(30);
        boolean found = false;

        System.out.println("\\nUPCOMING RENEWALS");
        System.out.println("-----------------");
        for (Subscription subscription : subscriptions) {
            LocalDate date = subscription.getRenewalDate();
            if (!date.isBefore(today) && !date.isAfter(limit)) {
                long daysLeft = ChronoUnit.DAYS.between(today, date);
                System.out.println(subscription.getName() + " - " + date
                        + " (" + (daysLeft == 0 ? "renews today" : daysLeft + " day(s) left") + ")");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No renewals due in the next 30 days.");
        }
    }

    private static void showMonthlyEstimate() {
        double total = 0;
        for (Subscription subscription : subscriptions) {
            String cycle = subscription.getBillingCycle().toLowerCase();
            if (cycle.startsWith("year")) {
                total += subscription.getCost() / 12.0;
            } else if (cycle.startsWith("week")) {
                total += subscription.getCost() * 52.0 / 12.0;
            } else {
                total += subscription.getCost();
            }
        }
        System.out.printf("Estimated monthly cost: $%.2f%n", total);
        System.out.println("Note: yearly costs are divided by 12; weekly costs use 52 weeks/year.");
    }

    private static void removeSubscription() {
        viewSubscriptions();
        if (subscriptions.isEmpty()) {
            return;
        }

        System.out.print("Enter the number of the subscription to remove: ");
        try {
            int number = Integer.parseInt(scanner.nextLine().trim());
            if (number < 1 || number > subscriptions.size()) {
                System.out.println("That number is not in the list.");
                return;
            }
            Subscription removed = subscriptions.remove(number - 1);
            System.out.println(removed.getName() + " removed.");
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }
}
