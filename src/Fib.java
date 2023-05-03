import parcs.*;

import java.util.ArrayList;

public class Fib implements AM{
    public String encrypt(String message, String key) {
        String encryptedMessage = "";
        message = message.toLowerCase();

        for (int i = 0, j = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (c < 'a' || c > 'z') continue;
            encryptedMessage += (char) ((c + key.charAt(j) - 2 * 'a') % 26 + 'a');
            j = ++j % key.length();
        }
        return encryptedMessage;
    }
    @Override
    public void run(AMInfo info){
        int i0;
        String key;
        ArrayList<String> array = new ArrayList<>();

        System.out.println("entering run1");
        Data data = (Data)info.parent.readObject();
        i0 = data.i0;
        key = data.key;
        array = data.array;
        System.out.println(i0 + "th thread");

        ArrayList<String> res = new ArrayList<>();
        for (int i=0;i<array.size(); i++)
        {
            res.add(encrypt(array.get(i),key));
        }
        Data d = new Data(i0, key, res);
        info.parent.write(d);
    }
}