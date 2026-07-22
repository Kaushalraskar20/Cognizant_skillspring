/**
 * Test class for the Singleton Logger.
 * Verifies that every call to getInstance() returns the SAME object.
 */
public class LoggerTest {
    public static void main(String[] args) {

        System.out.println("Requesting first Logger instance...");
        Logger logger1 = Logger.getInstance();
        logger1.log("Application started");

        System.out.println("\nRequesting second Logger instance...");
        Logger logger2 = Logger.getInstance();
        logger2.log("User logged in");

        // == checks object identity (same memory reference), not just
        // equal content. This is the real proof of a working Singleton.
        System.out.println("\nlogger1 == logger2 ? " + (logger1 == logger2));
        System.out.println("Total log entries recorded: " + logger1.getLogCount());
    }
}
