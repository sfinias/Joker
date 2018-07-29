package joker;

public class Main {

    public static void main(String[] args) {
        Player player1 = new Player("Jack", "Nickolson", 1469852, "GR15248");
        String menuMessage = "Press 1 for manual input, 2 for random ticket, 3 to check if your player has won, 4 to exit";
        while (true) {
            System.out.println(menuMessage);
            int in = Validation.readInt();
            switch (in) {
                case 1:
                    player1.play();
                    break;
                case 2:
                    player1.playRandom();
                    break;
                case 3:
                    WinningTicket wt = new WinningTicket();
                    if (player1.hasWon(wt)) {
                        System.out.println(player1.getFirstName() + " " + player1.getLastName() + " has won!");
                    } else {
                        System.out.println(player1.getFirstName() + " " + player1.getLastName() + " hasn't won");
                    }
                    break;
                default:
                    System.out.print("Thank you for playing!");
                    System.exit(0);
                }

        }
    }
}
