import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pque = new PriorityQueue<>((o1, o2) -> {
            int absO1 = Math.abs(o1);
            int absO2 = Math.abs(o2);

            if(absO1 == absO2) return o1 - o2;
            else return absO1 - absO2;
        });

        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            if(num == 0) {
                if(pque.isEmpty()) System.out.println(0);
                else System.out.println(pque.poll());
            } else {
                pque.offer(num);
            }
        }
    }
}
