package joker;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;


public class RandomTicket extends Ticket {

    private List<Integer> pool;
    
    public RandomTicket(int pickedNumbers, int pickedJokers){
        super(IDGen.getID());
        setMainNumbers(spinTheWheel(45,pickedNumbers));
        setJokerNumbers(spinTheWheel(20, pickedJokers));
        setPrice(calculatePrice());
    }

    public RandomTicket(int id){
        super(id);
        setMainNumbers(spinTheWheel(45,5));
        setJokerNumbers(spinTheWheel(20, 1));
        setPrice(calculatePrice());
    }

    private TreeSet<Integer> spinTheWheel(int poolSize, int pickedNumbers){
        fillPool(poolSize);
        TreeSet<Integer> set=new TreeSet<>();
        int size=poolSize;
        for(int i=0;i<pickedNumbers;i++){
            int n=(int) (Math.random()*size);
            set.add(pool.get(n));
            pool.remove(n);
            size--;
        }
        return set;
    }

    private void fillPool(int poolSize){
        pool= new ArrayList<>();
        for(int i=1;i<=poolSize;i++){
            pool.add(i);
        }
    }
}
