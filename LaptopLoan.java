/**
 * LaptopLoan is the second concrete Loan type.
 * The coursework rule for C = 4 is UGX 5,000 for every day late.
 */
public class LaptopLoan extends Loan {
    private static final double FINE_PER_DAY = 5000.0;

    public LaptopLoan(String id, String customerName, int daysLate, boolean firstTimeBorrower) {
        super(id, customerName, daysLate, firstTimeBorrower);
    }

    /** Normal laptop fine before any discount. */
    @Override
    protected double calculateNormalCharge() {
        return getDaysLate() * FINE_PER_DAY;
    }

    /** Apply the same 13% first-time-borrower discount rule. */
    @Override
    public double calculateCharge() {
        return applyDiscount(calculateNormalCharge());
    }

    @Override
    public String getType() {
        return "LaptopLoan";
    }
}
