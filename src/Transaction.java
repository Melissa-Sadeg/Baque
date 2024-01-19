import java.time.LocalDate;

public class Transaction {
    private final String type;
    private final double montant;
    private final LocalDate date;

    public Transaction(String type, double montant, LocalDate date) {
        this.type = type;
        this.montant = montant;
        this.date = date;
    }

    // Getters
    public String getType() {
        return type;
    }

    public double getMontant() {
        return montant;
    }

    public LocalDate getDate() {
        return date;
    }

    public String toString() {
        return "type: " + getType() + "; montant: " + getMontant() + "; date: " + getDate();
    }
}
