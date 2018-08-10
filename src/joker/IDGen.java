package joker;

//Singleton class that generates unique IDs for tickets
//We track differently the IDs for the tickets created by the player and the winning tickets
public class IDGen {
    private static IDGen instance;
    private int id;
    private int winningId;

    private IDGen() {
        this.id = 0;
        this.winningId = 0;
    }

    public static int getID() {
        if (instance == null) {
            instance = new IDGen();
        }
        instance.id++;
        return instance.id;
    }

    public static int getWinningId() {
        if (instance == null) {
            instance = new IDGen();
        }
        instance.winningId++;
        return instance.winningId;
    }
}
