package joker;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Ticket {

    private Set<Integer> mainNums=new TreeSet<>();
    private Set<Integer> jokerNums=new TreeSet<>();
    private Scanner scanner;
    private final String time;
    private final double price;
    private int id;

    public Ticket() {
        pickMain();
        pickJokers();
        this.time = getDate();
        this.price = calculatePrice();
        this.id = IDGen.getID();
    }

    public Set<Integer> getMainNums() {
        return mainNums;
    }

    public Set<Integer> getJokerNums() {
        return jokerNums;
    } 

    public String getTime() {
        return time;
    }

    public double getPrice() {
        return price;
    }
    
    private void pickMain(){
        while (true){
            System.out.println("Type in more than 5 unique numbers for the main numbers in the range 1-45");
            scanner = new Scanner(System.in);
            String str = scanner.nextLine();
            scanner = new Scanner(str);
            while(scanner.hasNext()){
                str = scanner.next();
                if (!str.matches("[0-9]+") || (Integer.valueOf(str)<1 || Integer.valueOf(str)>45)){
                    System.out.println("Can only accept numbers in the range 1-45");
                    mainNums.clear();
                    break;
                }
                int num = Integer.parseInt(str);
                if (mainNums.contains(num)){
                    System.out.println("You can't type in numbers duplicate times");
                    mainNums.clear();
                    break;
                }
                mainNums.add(num);
            }
            if (!mainNums.isEmpty() && mainNums.size()<5){
                System.out.println("You must type in at least 5 numbers");
                mainNums.clear();
            }else if (mainNums.size()>=5) {
                break;
            }
        }
        scanner.close();
    }
    
    private void pickJokers(){
       while (true){
            System.out.println("Type in more than 1 unique numbers for the joker in the range 1-20");
            scanner = new Scanner(System.in);
            String str = scanner.nextLine();
            scanner = new Scanner(str);
            while(scanner.hasNext()){
                str = scanner.next();
                if (!str.matches("[0-9]+") || (Integer.valueOf(str)<1 || Integer.valueOf(str)>20)){
                    System.out.println("Can only accept numbers in the range 1-20");
                    jokerNums.clear();
                    break;
                }
                int num = Integer.parseInt(str);
                if (jokerNums.contains(num)){
                    System.out.println("You can't type in numbers duplicate times");
                    jokerNums.clear();
                    break;
                }
                jokerNums.add(num);
            }
            if (jokerNums.size()>=1) {
                break;
            }
        }
        scanner.close();
    }
    
    private String getDate(){
        ZonedDateTime date = ZonedDateTime.now();
        return DateTimeFormatter.ofPattern("dd/MM/uuuu kk:mm:ss").format(date);
    }
    
    private double calculatePrice(){
        long mainCombinations = factorial(this.mainNums.size())/(factorial(5)*factorial(this.mainNums.size()-5));
        long jokerCombinations = this.jokerNums.size();
        return mainCombinations*jokerCombinations*0.5;
    }
    
    private long factorial(long n){
        long result = 1;
        for (long i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public boolean hasWon(WinningTicket winningTicket){
        int count = 0;
        for (int i:winningTicket.getMainNumbers()){
            if (this.mainNums.contains(i)) count++;
            if (count ==2) return true;
        }
        return false;
        
//        boolean mainWasFound = this.mainNums.containsAll(winningTicket.getMainNumbers());
//        boolean jokerWasFound = this.jokerNums.containsAll(winningTicket.getJokerNumbers());
//        return mainWasFound && jokerWasFound;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("Ticket ID: "+this.id);
        sb.append("\nTime played: "+this.time);
        sb.append("\nMain numbers: ");
        for (int i:mainNums){
            sb.append(i+" ");
        }
        sb.append("\nJokers: ");
        for (int i:jokerNums){
            sb.append(i+" ");
        }
        sb.append("\nPrice: "+this.price+"€");
        return sb.toString();
    }
}
