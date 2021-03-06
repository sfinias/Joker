package joker;

import java.util.HashSet;
import java.util.Set;


public class Player {

    private String firstName;
    private String lastName;
    private int taxNumber;
    private String iban;
    private Set<Ticket> ticketLog = new HashSet<>();

    public Player(String firstName, String lastName, int taxNumber, String iban) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.taxNumber = taxNumber;
        this.iban = iban;
    }

    public String getIban() {
        return iban;
    }

    public Set<Ticket> getTicketLog() {
        return ticketLog;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(int taxNumber) {
        this.taxNumber = taxNumber;
    }

    public void play() {
        ManualTicket ticket = new ManualTicket();
        System.out.println(ticket);
        ticketLog.add(ticket);
    }

    public void playRandom() {
        RandomTicket ticket = new RandomTicket();
        System.out.println(ticket);
        ticketLog.add(ticket);
    }

    public boolean hasWon(WinningTicket winningTicket) {
        boolean playerHasWon = false;
        for (Ticket t : ticketLog) {
            if (t.hasWon(winningTicket)) playerHasWon = true;
        }
        return playerHasWon;
    }
}
