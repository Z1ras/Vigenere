import java.io.Serializable;
import java.util.ArrayList;

public class Data implements Serializable {
    int i0;
    String key;
    ArrayList<String> array = new ArrayList<>();

    public Data(int i0, String key, ArrayList<String> array) {
        this.i0 = i0;
        this.key = key;
        this.array = array;
    }
}