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
            map.put(br.readLine(), 1);
        }

        for(int i = 0; i < M; i++) {
            String str = br.readLine();
            if(map.containsKey(str)) {
                map.replace(str, map.get(str)+1);
            }
        }

        int answer = -N;
        for(String s : map.keySet()) {
            answer += map.get(s);
        }

        System.out.println(answer);
    }
}
