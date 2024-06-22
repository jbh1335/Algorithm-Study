import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            if(str.length() < M) continue;

            map.put(str, map.getOrDefault(str, 0) + 1);
        }

        ArrayList<String> list = new ArrayList<>(map.keySet());
        Collections.sort(list, (o1, o2) -> {
            if(map.get(o1) == map.get(o2)) {
                if(o1.length() == o2.length()) {
                    return o1.compareTo(o2);
                }
                return o2.length() - o1.length();
            }
            return map.get(o2) - map.get(o1);
        });

        StringBuilder sb = new StringBuilder();
        for(String s : list) {
            sb.append(s + "\n");
        }

        System.out.println(sb);
    }
}
