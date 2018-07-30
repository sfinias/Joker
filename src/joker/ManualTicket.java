package joker;

import java.util.TreeSet;

public class ManualTicket extends Ticket {


    public ManualTicket() {
        super(IDGen.getID());
        pickMain();
        pickJokers();
        setPrice(calculatePrice());
    }

    private void pickMain() {
        while (true) {
            System.out.println("Type in more than " + MINPICKEDMAIN + " unique numbers for the main numbers in the range 1-" + MAINNUMBERPOOL);
            setMainNumbers(Validation.readInts(MAINNUMBERPOOL));
            if (!getMainNumbers().isEmpty() && getMainNumbers().size() < MINPICKEDMAIN) {
                System.out.println("You must type in at least " + MINPICKEDMAIN + " numbers");
                setMainNumbers(new TreeSet<>());
            } else if (getMainNumbers().size() >= MINPICKEDMAIN) {
                break;
            }
        }
    }

    private void pickJokers() {
        while (true) {
            System.out.println("Type in more than " + MINPICKEDJOKER + " unique numbers for the joker in the range 1-" + JOKERUMBERPOOL);
            setJokerNumbers(Validation.readInts(JOKERUMBERPOOL));
            if (getJokerNumbers().size() >= MINPICKEDJOKER) {
                break;
            }
        }
    }
}
