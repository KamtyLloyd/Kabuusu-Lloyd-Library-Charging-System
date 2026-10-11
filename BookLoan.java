/**
 * BookLoan is one concrete Loan type.
 * The coursework rule for C = 4 is UGX 500 for every day late.
 */
public class BookLoan extends Loan {
    private static final double FINE_PER_DAY = 500.0;

    public BookLoan(String id, String customerName, int daysLate, boolean firstTimeBorrower) {
        super(id, customerName, daysLate, firstTimeBorrower);
    }

    /** Normal book fine before any first-time-borrower discount. */
    @Override
    protected double calculateNormalCharge() {
        return getDaysLate() * FINE_PER_DAY;
    }

    /** Apply the 13% discount only when the borrower is a first-time borrower. */
    @Override
    public double calculateCharge() {
        return applyDiscount(calculateNormalCharge());
    }

    @Override
    public String getType() {
        return "BookLoan";
    }
}
