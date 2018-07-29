package joker;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class WinningTicket  {
    
   private List<Integer> pool;
   private Set<Integer> mainNumbers = new HashSet<>();
   private Set<Integer> jokerNumbers = new HashSet<>();
   
    public WinningTicket() {
        mainNumbers=spinTheWheel(45, 5);
        jokerNumbers=spinTheWheel(20, 1);
        System.out.println(this);
    }
    
    public WinningTicket(int pickedNumbers, int pickedJokers){
        mainNumbers=spinTheWheel(45, pickedNumbers);
        jokerNumbers=spinTheWheel(20, pickedJokers);
    }

    public Set<Integer> getMainNumbers() {
        return mainNumbers;
    }

    public Set<Integer> getJokerNumbers() {
        return jokerNumbers;
    }

    private Set spinTheWheel(int poolSize, int pickedNumbers){
        fillPool(poolSize);
        Set<Integer> set=new HashSet<>();
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
    
    public String toString(){
        StringBuilder sb = new StringBuilder("The winning numbers are ");
        for (int i:mainNumbers) {
            sb.append(i+" ");
        }
        sb.append("and the joker is ");
        for (int i:jokerNumbers) {
            sb.append(i+" ");
        }
        return sb.toString();
    }
}
