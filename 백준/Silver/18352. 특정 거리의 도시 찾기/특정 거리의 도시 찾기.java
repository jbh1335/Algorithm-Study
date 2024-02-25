import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K, X;
    static int[] distance;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static class Point {
        int city, dist;
        public Point(int city, int dist) {
            this.city = city;
            this.dist = dist;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 도시의 개수
        M = Integer.parseInt(st.nextToken()); // 도로의 개수
        K = Integer.parseInt(st.nextToken()); // 거리
        X = Integer.parseInt(st.nextToken()); // 출발 도시의 번호

        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        distance = new int[N+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
        }

        bfs();
        boolean exist = false;
        for(int i = 1; i <= N; i++) {
            if(distance[i] == K) {
                exist = true;
                System.out.println(i);
            }
        }
        if(!exist) System.out.println(-1);
    }

    public static void bfs() {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(X, 0));
        distance[X] = 0;

        while(!que.isEmpty()) {
            Point p = que.poll();

            for(int i : graph.get(p.city)) {
                int dist = p.dist + 1;
                if(dist <= K && dist < distance[i]) {
                    que.offer(new Point(i, dist));
                    distance[i] = dist;
                }
            }
        }
    }
}
