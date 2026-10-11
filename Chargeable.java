/**
 * Chargeable is the common contract for anything that can calculate a charge.
 * Both BookLoan and LaptopLoan ultimately provide this behaviour through Loan.
 */
public interface Chargeable {
    double calculateCharge();
}
