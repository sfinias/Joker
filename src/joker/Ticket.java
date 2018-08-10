package joker;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.TreeSet;

public abstract class Ticket {

    private Set<Integer> mainNumbers = new TreeSet<>();
    private Set<Integer> jokerNumbers = new TreeSet<>();
    private String time;
    private int id;
    private double price;
    public static final int MAINNUMBERPOOL = 45;
    public static final int JOKERUMBERPOOL = 20;
    public static final int MINPICKEDMAIN = 5;
    public static final int MINPICKEDJOKER = 1;

    public Ticket(int id) {
        this.id = id;
        this.time = timeStamp();
    }

    public Set<Integer> getMainNumbers() {
        return mainNumbers;
    }

    void setMainNumbers(Set<Integer> mainNumbers) {
        this.mainNumbers = mainNumbers;
    }

    public Set<Integer> getJokerNumbers() {
        return jokerNumbers;
    }

    void setJokerNumbers(Set<Integer> jokerNumbers) {
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

    private String timeStamp() {
        ZonedDateTime date = ZonedDateTime.now();
        return DateTimeFormatter.ofPattern("dd/MM/uuuu kk:mm:ss").format(date);
    }

    //Calculates the price for the ticket by using the combinations without repetitions formula
    //n!/r!*(n-r)!
    //Adjusted the code to avoid excessive loops
    public double calculatePrice() {
        long fact = 1;
        for (int i = this.mainNumbers.size(); i > this.mainNumbers.size() - MINPICKEDMAIN; i--) fact *= i;
        long mainCombinations = fact / factorial(MINPICKEDMAIN);
        long factj = 1;
        for (int i = this.jokerNumbers.size(); i > this.jokerNumbers.size() - MINPICKEDJOKER; i--) factj *= i;
        long jokerCombinations = factj / factorial(MINPICKEDJOKER);
        return mainCombinations * jokerCombinations * 0.5;
    }

    private long factorial(long n) {
        long result = 1;
        for (long i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    //Checks if this ticket has won by finding either all of the main numbers and jokers of the winning ticket
    //or by finding 4 of the main numbers
    public boolean hasWon(WinningTicket wt) {
        if (mainNumbers.containsAll(wt.getMainNumbers()) && jokerNumbers.containsAll(wt.getJokerNumbers())) {
            System.out.println("Ticket with id:" + id + " has won by finding all the numbers");
            return true;
        }
        int count = 0;
        for (int i : wt.getMainNumbers()) {
            if (getMainNumbers().contains(i)) count++;
        }
        if (count == 4) {
            System.out.println("Ticket with id:" + id + " has won by finding " + count + " of the main numbers");
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Ticket ID: " + this.id);
        sb.append("\nTime played: ").append(this.time);
        sb.append("\nMain numbers: ");
        for (int i : mainNumbers) {
            sb.append(i).append(" ");
        }
        sb.append("\nJokers: ");
        for (int i : jokerNumbers) {
            sb.append(i).append(" ");
        }
        sb.append("\nPrice: ").append(this.price).append("€");
        return sb.toString();
    }
}
