import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pque = new PriorityQueue<>();
        for(int i = 0; i < N; i++) {
            pque.offer(Integer.parseInt(br.readLine()));
        }

        int answer = 0;
        while(pque.size() >= 2) {
            int sum = pque.poll() + pque.poll();
            pque.offer(sum);
            answer += sum;
        }

        System.out.println(answer);
    }
}
