/**
 * Abstract parent class for every library loan record.
 * It contains the fields and validation shared by BookLoan and LaptopLoan.
 */
public abstract class Loan implements Chargeable {
    private final String id;
    private String customerName;
    private int daysLate;
    private boolean firstTimeBorrower;

    public Loan(String id, String customerName, int daysLate, boolean firstTimeBorrower) {
        if (id == null || id.isBlank()) {
            throw new InvalidLoanException("Loan ID cannot be blank.");
        }
        this.id = id.trim();
        setCustomerName(customerName);
        setDaysLate(daysLate);
        this.firstTimeBorrower = firstTimeBorrower;
    }

    public String getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getDaysLate() {
        return daysLate;
    }

    public boolean isFirstTimeBorrower() {
        return firstTimeBorrower;
    }

    /** Validate the borrower name before saving it. */
    public void setCustomerName(String customerName) {
        if (customerName == null || customerName.isBlank()) {
            throw new InvalidLoanException("Customer name cannot be blank.");
        }
        this.customerName = customerName.trim();
    }

    /** Negative late days are not allowed. Zero means the item is not late. */
    public void setDaysLate(int daysLate) {
        if (daysLate < 0) {
            throw new InvalidLoanException("Days late cannot be negative.");
        }
        this.daysLate = daysLate;
    }

    public void setFirstTimeBorrower(boolean firstTimeBorrower) {
        this.firstTimeBorrower = firstTimeBorrower;
    }

    /**
     * Kabuusu Lloyd's discount rate is 13% because B = 8 and P = B + 5.
     * A first-time borrower therefore pays 87% of the normal fine.
     */
    protected double applyDiscount(double normalCharge) {
        return firstTimeBorrower ? normalCharge * 0.87 : normalCharge;
    }

    /** How much discount was given on this record. */
    public double calculateDiscount() {
        return calculateNormalCharge() - calculateCharge();
    }

    /** Each subclass supplies its own normal fine calculation. */
    protected abstract double calculateNormalCharge();

    /** Used by the report to identify the subclass type. */
    public abstract String getType();

    @Override
    public String toString() {
        return String.format(
                "%s | %s | %s | Days late: %d | First-time: %s | Charge: UGX %,.0f",
                id,
                getType(),
                customerName,
                daysLate,
                firstTimeBorrower ? "Yes" : "No",
                calculateCharge()
        );
    }
}
