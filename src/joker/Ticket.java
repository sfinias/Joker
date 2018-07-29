package joker;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.TreeSet;

public abstract class Ticket {

    private Set<Integer> mainNumbers =new TreeSet<>();
    private Set<Integer> jokerNumbers =new TreeSet<>();
    private String time;
    private int id;
    private double price;

    public Ticket(int id) {
        this.id=id;
        this.time= timeStamp();
    }

    public Set<Integer> getMainNumbers() {
        return mainNumbers;
    }

    public void setMainNumbers(Set<Integer> mainNumbers) {
        this.mainNumbers = mainNumbers;
    }

    public Set<Integer> getJokerNumbers() {
        return jokerNumbers;
    }

    public void setJokerNumbers(Set<Integer> jokerNumbers) {
        this.jokerNumbers = jokerNumbers;
    }

    public String getTime() {
        return time;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private String timeStamp(){
        ZonedDateTime date = ZonedDateTime.now();
        return DateTimeFormatter.ofPattern("dd/MM/uuuu kk:mm:ss").format(date);
    }

    public double calculatePrice(){
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

    public boolean hasWon(WinningTicket winningTicket) {
        int count = 0;
        for (int i : winningTicket.getMainNumbers()) {
            if (getMainNumbers().contains(i)) count++;
            if (count == 2) return true;
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
        for (int i:mainNumbers){
            sb.append(i+" ");
        }
        sb.append("\nJokers: ");
        for (int i:jokerNumbers){
            sb.append(i+" ");
        }
        sb.append("\nPrice: "+this.price+"€");
        return sb.toString();
    }
}
