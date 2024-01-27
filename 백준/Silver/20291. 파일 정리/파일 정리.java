import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        HashMap<String, Integer> map = new HashMap<>();

        for(int tc = 1; tc <= N; tc++) {
            String[] split = br.readLine().split("[.]");
            map.put(split[1], map.getOrDefault(split[1], 0) + 1);
        }

        ArrayList<String> list = new ArrayList<>(map.keySet());
        Collections.sort(list);
        for(String s : list) {
            System.out.println(s + " " + map.get(s));
        }
    }
}
