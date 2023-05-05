import java.io.*;
import java.util.ArrayList;

import parcs.*;

public class Fibon implements AM {

    public static void main(String[] args) {
        task curtask = new task();
        curtask.addJarFile("Fib.jar");
        (new Fibon()).run(new AMInfo(curtask, (channel)null));
        curtask.end();
    }

    public void run(AMInfo info) {
        int n;
        String key;
        ArrayList<String> array = new ArrayList<>();
        try {
          /*byte[] buf = new byte[200];
          System.out.print("Enter n: ");
          System.in.read(buf);
          n=new Long(new String(buf).trim()).longValue();*/
            BufferedReader in = new BufferedReader(new FileReader(info.curtask.findFile("Fibon.data")));
            key = new String(in.readLine());
            n = Integer.parseInt((in.readLine()));
            for (int i=0; i<n; i++)
            {
                array.add(in.readLine());
            }
        } catch (IOException e) {e.printStackTrace(); return;}

        int k = 2;
        point[] p = new point[k];
        channel[] c = new channel[k];
        int size = array.size();
        int chunkSize = (int) Math.ceil((double) size / k);
        for (int i=0; i<k; i++)
        {
            p[i] = info.createPoint();
            c[i] = p[i].createChannel();
            p[i].execute("Fib");

            ArrayList<String> arr = new ArrayList<>(array.subList(i*chunkSize, Math.min(size, i*chunkSize + chunkSize)));
            Data d = new Data(i, key, arr);
            c[i].write(d);
        }

        System.out.println("Waiting for result...");
        ArrayList<String> res = new ArrayList<>();
        for (int i=0; i<k; i++)
        {
            Data data = (Data)c[i].readObject();
            System.out.println(data.array.size());
            System.out.println("Last word from subarray:");
            System.out.println(data.array.get(data.array.size()-1));
            res.addAll(data.array);
        }

        try{
            PrintWriter out = new PrintWriter(new FileWriter(info.curtask.addPath("Fibon.res")));
            for (int i=0; i<res.size(); i++)
            {
                out.println(res.get(i));
            }
            out.close();
        } catch (IOException e) {e.printStackTrace(); return;}
    }
}
