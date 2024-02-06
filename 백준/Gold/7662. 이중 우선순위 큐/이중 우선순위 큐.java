import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            int k = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> map = new TreeMap<>();

            for(int i = 0; i < k; i++) {
                String str = br.readLine();
                String[] splitArr = str.split(" ");
                int num = Integer.parseInt(splitArr[1]);

                if(splitArr[0].equals("I")) {
                    map.put(num, map.getOrDefault(num, 0)+1);
                } else {
                    if(map.isEmpty()) continue;

                    if(num == -1) delete(map.firstKey(), map);
                    else delete(map.lastKey(), map);
                }
            }

            if(map.isEmpty()) System.out.println("EMPTY");
            else System.out.println(map.lastKey() + " " + map.firstKey());
        }
    }

    public static void delete(int key, TreeMap<Integer, Integer> map) {
        if(map.get(key) == 1) map.remove(key);
        else map.put(key, map.get(key)-1);
    }
}
