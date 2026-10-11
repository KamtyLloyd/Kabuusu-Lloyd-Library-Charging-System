/**
 * Custom exception used when invalid loan data is entered.
 * Keeping a business-specific exception makes error messages easier to understand.
 */
public class InvalidLoanException extends RuntimeException {
    public InvalidLoanException(String message) {
        super(message);
    }
}
