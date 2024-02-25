import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int answer = 0;

        if(start != end) {
            answer = start < end ? bfs(start, end, new int[end*2]) : start - end;
        }
        System.out.println(answer);
    }

    public static int bfs(int start, int end, int[] distance) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(start);
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        while(!que.isEmpty()) {
            int x = que.poll();
            if(x == end) break;
            int[] dx = {-1, 1, x};

            for(int i = 0; i < 3; i++) {
                int nx = x + dx[i];

                if(nx >= 0 && nx < distance.length) {
                    int dist = distance[x] + 1;
                    if(i == 2) dist--;

                    if(dist < distance[nx]) {
                        que.offer(nx);
                        distance[nx] = dist;
                    }
                }
            }
        }
        return distance[end];
    }
}
