package joker;


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
