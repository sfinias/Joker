package joker;


import java.util.HashSet;
import java.util.Set;

public class WinningTicket extends RandomTicket  {

    public static Set<WinningTicket> winningTickets = new HashSet<>();

    public WinningTicket() {
        super(IDGen.getWinningId());
        System.out.println(this);
        winningTickets.add(this);
    }

    @Override
    public String toString(){
        String st = "New winning ticket with id:"+super.getId()+". The winning numbers are ";
        for (int i:getMainNumbers()) {
            st+=i+" ";
        }
        st+="and the joker is ";
        for (int i:getJokerNumbers()) {
            st+= i+" ";
        }
        return st;
    }
}
