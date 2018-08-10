package joker;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class RandomTicket extends Ticket {

    public RandomTicket() {
        super(IDGen.getID());
        int mainNumbers = pickMainNumbers();
        int jokerNumbers = pickJokerNumbers();
        setMainNumbers(spinTheWheel(MAINNUMBERPOOL, mainNumbers));
        setJokerNumbers(spinTheWheel(JOKERUMBERPOOL, jokerNumbers));
        setPrice(calculatePrice());
    }

    public RandomTicket(int id, Set<Integer> mainSet, Set<Integer> jokerSet) {
        super(id);
        setMainNumbers(mainSet);
        setJokerNumbers(jokerSet);
    }

    //Picks random #pickedNumbers unique numbers from 1 to #poolSize
    //The list pool is filled with these numbers and then it randomly picks a random number from the list,
    //adds it to the set and deletes it from the list
    private Set<Integer> spinTheWheel(int poolSize, int pickedNumbers) {
        List<Integer> pool = new ArrayList<>();
        for (int i = 1; i <= poolSize; i++) {
            pool.add(i);
        }
        Set<Integer> set = new TreeSet<>();
        int size = poolSize;
        for (int i = 0; i < pickedNumbers; i++) {
            int n = (int) (Math.random() * size);
            set.add(pool.get(n));
            pool.remove(n);
            size--;
        }
        return set;
    }

    private int pickMainNumbers() {
        System.out.println("Type how many random main numbers you want to be picked in the range " + MINPICKEDMAIN + "-" + MAINNUMBERPOOL);
        return Validation.readLimitInt(MINPICKEDMAIN, MAINNUMBERPOOL);
    }

    private int pickJokerNumbers() {
        System.out.println("Type how many random jokers you want to be picked in the range " + MINPICKEDJOKER + "-" + JOKERUMBERPOOL);
        return Validation.readLimitInt(MINPICKEDJOKER, JOKERUMBERPOOL);
    }
}
