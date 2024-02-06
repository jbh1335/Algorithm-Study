import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pque = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for(int i = 0 ; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                pque.offer(Integer.parseInt(st.nextToken()));
            }
        }

        int answer = 0;
        for(int i = 0; i < N; i++) {
            answer = pque.poll();
        }
        System.out.println(answer);
    }
}
