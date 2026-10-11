import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * LoanManager stores and manages all loan records in one ArrayList.
 * This class demonstrates collections, searching, removal, totals and Comparator sorting.
 */
public class LoanManager {
    private final ArrayList<Loan> loans = new ArrayList<>();
    private final String idPrefix;
    private int nextNumber = 1;

    public LoanManager(String idPrefix) {
        if (idPrefix == null || idPrefix.isBlank()) {
            throw new IllegalArgumentException("ID prefix cannot be blank.");
        }
        this.idPrefix = idPrefix;
    }

    /** Generate IDs such as KL784-001, KL784-002 and so on. */
    public String generateId() {
        return String.format("%s-%03d", idPrefix, nextNumber++);
    }

    public void addLoan(Loan loan) {
        if (loan == null) {
            throw new InvalidLoanException("Loan cannot be null.");
        }
        loans.add(loan);
    }

    /** Find one record by ID. Returns null when it does not exist. */
    public Loan findById(String id) {
        for (Loan loan : loans) {
            if (loan.getId().equalsIgnoreCase(id)) {
                return loan;
            }
        }
        return null;
    }

    /** Remove a loan by ID. The Main menu asks for confirmation first. */
    public boolean removeById(String id) {
        Loan loan = findById(id);
        if (loan == null) {
            return false;
        }
        return loans.remove(loan);
    }

    /** Return a safe copy so outside code cannot directly edit the stored ArrayList. */
    public List<Loan> getAllLoans() {
        return new ArrayList<>(loans);
    }

    /**
     * Polymorphism: item is a Chargeable reference, but Java runs the correct
     * BookLoan or LaptopLoan calculateCharge() method at runtime.
     */
    public double getTotalCharges() {
        double total = 0;
        for (Chargeable item : loans) {
            total += item.calculateCharge();
        }
        return total;
    }

    public double getTotalDiscount() {
        double total = 0;
        for (Loan loan : loans) {
            total += loan.calculateDiscount();
        }
        return total;
    }

    /**
     * A = 7, so the main report is sorted by record type first,
     * then customer name alphabetically using thenComparing.
     */
    public List<Loan> getSortedReport() {
        Comparator<Loan> byTypeThenName = Comparator
                .comparing(Loan::getType)
                .thenComparing(Loan::getCustomerName, String.CASE_INSENSITIVE_ORDER);

        return loans.stream()
                .sorted(byTypeThenName)
                .toList();
    }

    public long getBookLoanCount() {
        return loans.stream().filter(l -> l instanceof BookLoan).count();
    }

    public long getLaptopLoanCount() {
        return loans.stream().filter(l -> l instanceof LaptopLoan).count();
    }
}
