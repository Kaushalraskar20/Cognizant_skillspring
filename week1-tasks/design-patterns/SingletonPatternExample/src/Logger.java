/**
 * Logger - a Singleton class.
 *
 * Guarantees that only ONE instance of Logger exists for the entire
 * application lifecycle, so all parts of the app write logs through
 * the same object (consistent state, no duplicate log handlers, etc.)
 */
public class Logger {

    // The single instance, held in a static field so it belongs to the
    // class itself rather than to any particular object.
    // Lazy initialization: it stays null until someone actually asks for it.
    private static Logger instance;

    // Example of internal state the Logger might track.
    private int logCount;

    // Private constructor: this is the key to Singleton.
    // No other class can do "new Logger()" because the constructor
    // is not visible outside this class.
    private Logger() {
        logCount = 0;
        System.out.println("Logger instance created.");
    }

    /**
     * The single public access point to the Logger.
     * Must be static, because at the time you call it you don't have
     * an instance yet - that's exactly the problem it's solving.
     */
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        logCount++;
        System.out.println("[LOG #" + logCount + "] " + message);
    }

    public int getLogCount() {
        return logCount;
    }
}
