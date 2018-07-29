package joker;


public class IDGen {
    private static IDGen instance;
    private int id;

    private IDGen() {
        this.id = 0;
    }
    
    public static int getID(){
        if (instance==null){
            instance = new IDGen();
        }
        instance.id++;
        return instance.id;
    }
    
}
