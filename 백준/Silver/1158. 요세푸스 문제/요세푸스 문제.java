import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Integer> que = new LinkedList<>();
        for(int i = 1; i <= N; i++) {
            que.offer(i);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<");
        int count = 0;
        while(true) {
            int num = que.poll();
            if(que.size() == 0) {
                sb.append(num + ">");
                break;
            }

            if(++count == K) {
                sb.append(num + ", ");
                count = 0;
            } else {
                que.offer(num);
            }
        }

        System.out.println(sb);
    }
}
