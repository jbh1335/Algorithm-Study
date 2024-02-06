import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Integer> map = new HashMap<>();
        int total = 0;

        while(true) {
            String str = br.readLine();
            if(str == null || str.equals("")) break;
            map.put(str, map.getOrDefault(str, 0) + 1);
            total++;
        }

        ArrayList<String> list = new ArrayList<>(map.keySet());
        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        for(String s : list) {
            sb.append(s + " " + String.format("%.4f", (double) map.get(s)/total*100) + "\n");
        }

        System.out.println(sb);
    }
}
