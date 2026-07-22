import java.util.Arrays;
import java.util.Comparator;

/**
 * SearchDemo - implements and compares Linear Search and Binary Search
 * for the e-commerce product search function.
 *
 * Linear Search:
 *   - Works on data in ANY order (sorted or not).
 *   - Checks every element one by one until a match is found.
 *   - Time complexity: O(n) worst case, O(1) best case (first element).
 *
 * Binary Search:
 *   - Requires the data to be SORTED first (by the key you're searching on).
 *   - Repeatedly halves the search range by comparing against the middle element.
 *   - Time complexity: O(log n) worst case, O(1) best case.
 *
 * For a search feature on an e-commerce platform with a large, mostly
 * static catalog, Binary Search is far more suitable once the catalog is
 * sorted (e.g. by productId), because O(log n) scales dramatically better
 * than O(n) as the catalog grows into the tens of thousands of products.
 * Linear Search only makes sense for small, unsorted, or frequently
 * changing datasets where the cost of sorting outweighs the search savings.
 */
public class SearchDemo {

    /**
     * Linear search by productId. Returns the index of the match, or -1.
     * Counts comparisons to make the cost visible.
     */
    public static int linearSearch(Product[] products, int targetId) {
        int comparisons = 0;
        for (int i = 0; i < products.length; i++) {
            comparisons++;
            if (products[i].getProductId() == targetId) {
                System.out.println("Linear search comparisons: " + comparisons);
                return i;
            }
        }
        System.out.println("Linear search comparisons: " + comparisons);
        return -1;
    }

    /**
     * Binary search by productId. ASSUMES the array is already sorted
     * by productId in ascending order. Returns the index of the match, or -1.
     */
    public static int binarySearch(Product[] sortedProducts, int targetId) {
        int low = 0;
        int high = sortedProducts.length - 1;
        int comparisons = 0;

        while (low <= high) {
            comparisons++;
            int mid = low + (high - low) / 2;
            int midId = sortedProducts[mid].getProductId();

            if (midId == targetId) {
                System.out.println("Binary search comparisons: " + comparisons);
                return mid;
            } else if (midId < targetId) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        System.out.println("Binary search comparisons: " + comparisons);
        return -1;
    }

    public static void main(String[] args) {

        // Unsorted catalog - fine for linear search.
        Product[] catalog = {
            new Product(105, "Wireless Mouse", "Electronics"),
            new Product(102, "Bluetooth Speaker", "Electronics"),
            new Product(110, "Yoga Mat", "Fitness"),
            new Product(101, "Running Shoes", "Fitness"),
            new Product(108, "Desk Lamp", "Home"),
            new Product(103, "Backpack", "Accessories"),
            new Product(107, "Water Bottle", "Fitness")
        };

        int targetId = 108;

        System.out.println("=== Linear Search (unsorted array) ===");
        int linearIndex = linearSearch(catalog, targetId);
        printResult(catalog, linearIndex);

        System.out.println("\n=== Binary Search (requires sorted array) ===");
        // Sort a copy of the catalog by productId before binary search.
        Product[] sortedCatalog = Arrays.copyOf(catalog, catalog.length);
        Arrays.sort(sortedCatalog, Comparator.comparingInt(Product::getProductId));

        int binaryIndex = binarySearch(sortedCatalog, targetId);
        printResult(sortedCatalog, binaryIndex);

        // Demonstrate the gap widening on a larger, sorted dataset.
        System.out.println("\n=== Comparison on a larger dataset (1000 products) ===");
        Product[] bigCatalog = new Product[1000];
        for (int i = 0; i < bigCatalog.length; i++) {
            bigCatalog[i] = new Product(i + 1, "Product" + (i + 1), "General");
        }
        int searchFor = 987; // near the end - worst case for linear search

        System.out.print("Linear -> ");
        linearSearch(bigCatalog, searchFor);

        System.out.print("Binary -> ");
        binarySearch(bigCatalog, searchFor); // already sorted by construction
    }

    private static void printResult(Product[] products, int index) {
        if (index == -1) {
            System.out.println("Product not found.");
        } else {
            System.out.println("Found at index " + index + ": " + products[index]);
        }
    }
}
