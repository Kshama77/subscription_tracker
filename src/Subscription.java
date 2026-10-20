import java.time.LocalDate;

public class Subscription {
    private String name;
    private double cost;
    private String billingCycle;
    private LocalDate renewalDate;

    public Subscription(String name, double cost, String billingCycle, LocalDate renewalDate) {
        this.name = name;
        this.cost = cost;
        this.billingCycle = billingCycle;
        this.renewalDate = renewalDate;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public String getBillingCycle() {
        return billingCycle;
    }

    public LocalDate getRenewalDate() {
        return renewalDate;
    }

    public void setRenewalDate(LocalDate renewalDate) {
        this.renewalDate = renewalDate;
    }

    @Override
    public String toString() {
        return String.format("%-20s $%-9.2f %-12s %s",
                name, cost, billingCycle, renewalDate);
    }
}
