package joker;

import java.util.Scanner;
import java.util.TreeSet;

public class Validation {

    private static Scanner scanner;

    private Validation() {
    }

    public static int readInt() {
        scanner = new Scanner(System.in);
        String st = scanner.nextLine();
        if (st.isEmpty()) return -1;
        scanner = new Scanner(st);
        st = scanner.next();
        if (scanner.hasNext() || !st.matches("[0-9]+")) {
            return -1;
        }
        return Integer.valueOf(st);
    }

    public static int readLimitInt(int minPicked, int maxPicked) {
        while (true) {
            int mainNumbers = readInt();
            if (mainNumbers < minPicked || mainNumbers > maxPicked) {
                System.out.println("You must pick from " + minPicked + " to " + maxPicked + " numbers");
            } else {
                return mainNumbers;
            }
        }
    }

    public static TreeSet<Integer> readInts(int max) {
        TreeSet<Integer> set = new TreeSet<>();
        scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        scanner = new Scanner(str);
        while (scanner.hasNext()) {
            str = scanner.next();
            if (!str.matches("[0-9]+") || (Integer.valueOf(str) < 1 || Integer.valueOf(str) > max)) {
                System.out.println("Can only accept numbers in the range 1-" + max);
                set.clear();
                break;
            }
            int num = Integer.parseInt(str);
            if (!set.add(num)) {
                System.out.println("You can't type in duplicate numbers");
                set.clear();
                break;
            }
        }
        return set;
    }
}
