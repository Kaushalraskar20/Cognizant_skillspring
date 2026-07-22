import java.util.HashMap;
import java.util.Map;

/**
 * FinancialForecast - predicts a future value from a present value and
 * a constant annual growth rate, using recursion.
 *
 * The relationship being modeled is compound growth:
 *     futureValue(year) = futureValue(year - 1) * (1 + growthRate)
 *     futureValue(0)     = presentValue   <-- base case
 *
 * This is a textbook example of a recursive algorithm: a problem
 * (value after N years) defined in terms of a smaller version of
 * itself (value after N-1 years), plus a base case that stops the
 * recursion (year 0 = the known present value).
 *
 * Time complexity (naive recursion above): O(n)
 *   - Each call does O(1) work and there are n+1 calls total
 *     (year, year-1, ..., 0). Despite "recursion sounding expensive,"
 *     THIS particular recursion is already linear because each year
 *     is only ever computed once down a single call chain.
 *
 * Where recursion gets genuinely expensive is when the same sub-problem
 * is recomputed many times (e.g. naive Fibonacci, where fib(n-1) and
 * fib(n-2) each separately recompute overlapping smaller calls,
 * giving O(2^n)). This forecasting recursion doesn't have that overlap,
 * so memoization doesn't change its complexity - but it's included below
 * to show HOW you'd optimize a recursive algorithm that does have repeated
 * subproblems, using a cache (Map) to store and reuse already-computed results.
 */
public class FinancialForecast {

    // Cache for memoized version: key = year, value = computed future value.
    private static Map<Integer, Double> cache = new HashMap<>();

    /**
     * Naive recursive future value calculation.
     *
     * @param presentValue current amount (e.g. an investment or revenue figure)
     * @param growthRate   annual growth rate as a decimal (e.g. 0.08 for 8%)
     * @param year         how many years ahead to project
     */
    public static double futureValue(double presentValue, double growthRate, int year) {
        if (year == 0) {
            // Base case: stops the recursion.
            return presentValue;
        }
        // Recursive case: this year's value depends on last year's value.
        return futureValue(presentValue, growthRate, year - 1) * (1 + growthRate);
    }

    /**
     * Memoized version - caches results so repeated calls for the same
     * year (e.g. if called from a loop for many forecasts) aren't recomputed.
     */
    public static double futureValueMemoized(double presentValue, double growthRate, int year) {
        if (year == 0) {
            return presentValue;
        }
        if (cache.containsKey(year)) {
            return cache.get(year);
        }
        double result = futureValueMemoized(presentValue, growthRate, year - 1) * (1 + growthRate);
        cache.put(year, result);
        return result;
    }

    public static void main(String[] args) {
        double presentValue = 100000; // e.g. current revenue figure
        double growthRate = 0.08;     // 8% annual growth

        System.out.println("=== Financial Forecast (Recursive) ===");
        for (int year = 1; year <= 5; year++) {
            double value = futureValue(presentValue, growthRate, year);
            System.out.printf("Year %d: %.2f%n", year, value);
        }

        System.out.println("\n=== Same forecast using the memoized version ===");
        cache.clear();
        for (int year = 1; year <= 5; year++) {
            double value = futureValueMemoized(presentValue, growthRate, year);
            System.out.printf("Year %d: %.2f%n", year, value);
        }
    }
}
