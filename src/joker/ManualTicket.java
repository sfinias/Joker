package joker;

import java.util.Scanner;
import java.util.TreeSet;

public class ManualTicket extends Ticket {


    private Scanner scanner;


    public ManualTicket() {
        super(IDGen.getID());
        pickMain();
        pickJokers();
        setPrice(calculatePrice());
    }

    private void pickMain(){
        int max = 45;
        int min = 1;
        int count = 5;
        while (true){
            System.out.println("Type in more than " + count + " unique numbers for the main numbers in the range " + min + "-" + max);
            setMainNumbers(Validation.readInts(min, max));
            if (!getMainNumbers().isEmpty() && getMainNumbers().size()<5){
                System.out.println("You must type in at least 5 numbers");
                setMainNumbers(new TreeSet<>());
            }else if (getMainNumbers().size()>=5) {
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
           setJokerNumbers(Validation.readInts(min, max));
           if (getJokerNumbers().size()>=1) {
               break;
           }
        }
    }
}
