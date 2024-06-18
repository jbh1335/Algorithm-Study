import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            String str = br.readLine();
            int K = Integer.parseInt(br.readLine());
            if(K == 1) {
                System.out.println("1 1");
                continue;
            }

            HashMap<Character, Integer> map = new HashMap<>();
            for(int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                map.put(ch, map.getOrDefault(ch, 0) + 1);
            }

            int min = Integer.MAX_VALUE, max = -1;
            for(int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                if(map.get(ch) < K) continue;

                int count = 1;
                for(int j = i+1; j < str.length(); j++) {
                    if(str.charAt(j) == ch) count++;
                    if(count == K) {
                        min = Math.min(min, j-i+1);
                        max = Math.max(max, j-i+1);
                        break;
                    }
                }
            }

            if(min == Integer.MAX_VALUE && max == -1) System.out.println(-1);
            else System.out.println(min + " " + max);
        }
    }
}
