package joker;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Ticket {

    private Set<Integer> mainNumbers =new TreeSet<>();
    private Set<Integer> jokerNumbers =new TreeSet<>();
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

    public Set<Integer> getMainNumbers() {
        return mainNumbers;
    }

    public Set<Integer> getJokerNumbers() {
        return jokerNumbers;
    } 

    public String getTime() {
        return time;
    }

    public double getPrice() {
        return price;
    }
    
    private void pickMain(){
        int max = 45;
        int min = 1;
        int count = 5;
        while (true){
            System.out.println("Type in more than " + count + " unique numbers for the main numbers in the range " + min + "-" + max);
            this.mainNumbers = Validation.readInts(min, max);
            if (!mainNumbers.isEmpty() && mainNumbers.size()<5){
                System.out.println("You must type in at least 5 numbers");
                mainNumbers.clear();
            }else if (mainNumbers.size()>=5) {
                break;
            }
        }
    }
    
    private void pickJokers(){
        int max = 20;
        int min = 1;
        int count = 1;
       while (true){
           System.out.println("Type in more than " + count + " unique numbers for the joker in the range 1-20");
           jokerNumbers = Validation.readInts(min, max);
           if (jokerNumbers.size()>=1) {
               break;
           }
        }
    }
    
    private String getDate(){
        ZonedDateTime date = ZonedDateTime.now();
        return DateTimeFormatter.ofPattern("dd/MM/uuuu kk:mm:ss").format(date);
    }
    
    private double calculatePrice(){
        long mainCombinations = factorial(this.mainNumbers.size())/(factorial(5)*factorial(this.mainNumbers.size()-5));
        int jokerCombinations = this.jokerNumbers.size();
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
            if (this.mainNumbers.contains(i)) count++;
            if (count ==2) return true;
        }
        return false;
        
//        boolean mainWasFound = this.mainNumbers.containsAll(winningTicket.getMainNumbers());
//        boolean jokerWasFound = this.jokerNumbers.containsAll(winningTicket.getJokerNumbers());
//        return mainWasFound && jokerWasFound;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("Ticket ID: "+this.id);
        sb.append("\nTime played: "+this.time);
        sb.append("\nMain numbers: ");
        for (int i: mainNumbers){
            sb.append(i+" ");
        }
        sb.append("\nJokers: ");
        for (int i: jokerNumbers){
            sb.append(i+" ");
        }
        sb.append("\nPrice: "+this.price+"€");
        return sb.toString();
    }
}
