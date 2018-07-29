package joker;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Player player1 = new Player("Jack", "Nickolson", 1469852, "GR15248");
        int in=0;
        String st;
        Scanner scanner = new Scanner(System.in);
        while (in!=4){  
            while (true){
                System.out.println("Press 1 for manual input, 2 for random ticket, 3 to check if your player has won, 4 to exit");
                st = scanner.nextLine();
                scanner=new Scanner(st);
                st = scanner.next();
                if(scanner.hasNext() || !st.matches("[0-9]+") || Integer.valueOf(st)<1 || Integer.valueOf(st)>4){
                    System.out.println("Wrong input");
                    scanner = new Scanner(System.in);
                }else{
                    in=Integer.valueOf(st);
                    scanner=new Scanner(System.in);
                    break;
                }
            }
            switch (in) {
                case 1:
                    player1.play();
                    break;
                case 2:
                    while (true){
                        System.out.println("Type how many main numbers and jokers you want to pick");
                        int mainNum;
                        int jokerNums;
                        st = scanner.nextLine();
                        scanner = new Scanner(st);
                        st = scanner.next();
                        if(!scanner.hasNext() || !st.matches("[0-9]+") || Integer.valueOf(st)<5 || Integer.valueOf(st)>45){
                            System.out.println("Wrong input");
                            scanner = new Scanner(System.in);
                            continue;
                        }else{
                            mainNum=Integer.valueOf(st);
                        }
                        st = scanner.next();
                        if(scanner.hasNext() || !st.matches("[0-9]+") || Integer.valueOf(st)<1 || Integer.valueOf(st)>20){
                            System.out.println("Wrong input");
                            scanner = new Scanner(System.in);
                        }else{
                            jokerNums=Integer.valueOf(st);
                            scanner=new Scanner(System.in);
                            player1.playRandom(mainNum, jokerNums);
                            break;
                        }   
                    }
                    break;
                case 3:
                    WinningTicket wt = new WinningTicket();
                    if (player1.hasWon(wt)){
                        System.out.println(player1.getFirstName()+" "+player1.getLastName()+" has won!");
                    }else{
                        System.out.println(player1.getFirstName()+" "+player1.getLastName()+" hasn't won");
                    }   break;
                default:
                    System.out.println("Thank you for playing!");
            }
        }
    }
}
