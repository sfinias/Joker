package joker;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public class RandomTicket extends WinningTicket {
    
    private final String time;
    private final double price;
    private int id;
    
    public RandomTicket(int pickedNumbers, int pickedJokers){
        super(pickedNumbers,pickedJokers);
        this.time = getDate();
        this.price = calculatePrice();
        this.id = IDGen.getID();
    }
    
    private String getDate(){
        ZonedDateTime date = ZonedDateTime.now();
        return DateTimeFormatter.ofPattern("dd/MM/uuuu kk:mm:ss").format(date);
    }
    
    private double calculatePrice(){
        long mainCombinations = factorial(getMainNumbers().size())/(factorial(5)*factorial(getMainNumbers().size()-5));
        long jokerCombinations = getJokerNumbers().size();
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
            if (getMainNumbers().contains(i)) count++;
            if (count ==2) return true;
        }
        return false;
//        boolean mainWasFound = getMainNumbers().containsAll(winningTicket.getMainNumbers());
//        boolean jokerWasFound = getJokerNumbers().containsAll(winningTicket.getJokerNumbers());
//        return mainWasFound && jokerWasFound;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("Ticket ID: "+this.id);
        sb.append("\nTime played: "+this.time);
        sb.append("\nMain numbers: ");
        for (int i:getMainNumbers()){
            sb.append(i+" ");
        }
        sb.append("\nJokers: ");
        for (int i:getJokerNumbers()){
            sb.append(i+" ");
        }
        sb.append("\nPrice: "+this.price+"€");
        return sb.toString();
    }
}
