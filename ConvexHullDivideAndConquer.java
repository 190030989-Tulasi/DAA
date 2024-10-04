package demo_1;
import java.util.*;

public class ConvexHullDivideAndConquer {
    public static List<int[]> convexHull(int[][] points) {
        if (points.length < 3) {
            return Arrays.asList(points);
        }
        Arrays.sort(points,
                    (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        List<int[]> upper = new ArrayList<>();
        List<int[]> lower = new ArrayList<>();
        for (int[] point : points) {
            while (upper.size() >= 2 && isNotRightTurn(upper.get(upper.size() - 2), upper.get(upper.size() - 1), point)) {
                upper.remove(upper.size() - 1);
            }
            upper.add(point);
        }

        for (int i = points.length - 1; i >= 0; i--) {
            int[] point = points[i];
            while (lower.size() >= 2 && isNotRightTurn(lower.get(lower.size() - 2), lower.get(lower.size() - 1), point)) {
                lower.remove(lower.size() - 1);
            }
            lower.add(point);
        }
        HashSet<int[]> hull = new HashSet<>(upper);
        hull.addAll(lower);
        return new ArrayList<>(hull);
    }
    
    private static boolean isNotRightTurn(int[] a, int[] b, int[] c) {
        return (b[0] - a[0]) * (c[1] - a[1]) - (b[1] - a[1]) * (c[0] - a[0]) <= 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of points (n): ");
        int n = scanner.nextInt();
        int[][] points = generateRandomPoints(n);
        long startTime = System.nanoTime();
        List<int[]> hull = convexHull(points);
        long endTime = System.nanoTime();
        long duration = endTime - startTime; // Duration in nanoseconds
        System.out.println("Original Points:");
        for (int[] point : points) {
            System.out.println(point[0] + " " + point[1]);
        }
        System.out.println("\nConvex Hull:");
        for (int[] point : hull) {
            System.out.println(point[0] + " " + point[1]);
        }
        System.out.println("\nExecution time: " + duration + " nanoseconds");
    }

    private static int[][] generateRandomPoints(int n) {
        Random random = new Random();
        int[][] points = new int[n][2];
        for (int i = 0; i < n; i++) {
            points[i][0] = random.nextInt(21) - 10; // Random x-coordinate between -10 and 10
            points[i][1] = random.nextInt(21) - 10; // Random y-coordinate between -10 and 10
        }
        return points;
    }
}
