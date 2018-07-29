package joker;

import java.util.Scanner;
import java.util.TreeSet;

public class Validation {

    private static Scanner scanner;

    private Validation(){}

    public static int readInt(){
        scanner = new Scanner(System.in);
        String st = scanner.nextLine();
        scanner=new Scanner(st);
        st = scanner.next();
        if(scanner.hasNext() || !st.matches("[0-9]+")){
            return -1;
        }
        return Integer.valueOf(st);
    }

    public static int readMainNumbers(){
        while (true) {
            System.out.println("Type how many main numbers you want to pick");
            int mainNumbers = readInt();
            if (mainNumbers<5 || mainNumbers>45) {
                System.out.println("5 to 45 numbers must be picked");
            }else{
                return mainNumbers;
            }
        }
    }

    public static int readJokerNumbers(){
        while(true){
            System.out.println("Type how many jokers you want to pick");
            int jokerNumbers = readInt();
            if (jokerNumbers<1 || jokerNumbers>20) {
                System.out.println("1 to 20 numbers must be picked");
            }else {
                return jokerNumbers;
            }
        }
    }

    public static TreeSet<Integer> readInts(int min, int max){
        TreeSet<Integer> set = new TreeSet<>();
        scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        scanner = new Scanner(str);
        while(scanner.hasNext()){
            str = scanner.next();
            if (!str.matches("[0-9]+") || (Integer.valueOf(str)<min || Integer.valueOf(str)>max)){
                System.out.println("Can only accept numbers in the range " + min + "-" + max);
                set.clear();
                break;
            }
            int num = Integer.parseInt(str);
            if (!set.add(num)){
                System.out.println("You can't type in numbers duplicate times");
                set.clear();
                break;
            }
        }
        return set;
    }
}
