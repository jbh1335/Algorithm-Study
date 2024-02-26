import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] minCost;
    static ArrayList<Point>[] connectList;
    static class Point {
        int bus, cost;
        public Point(int bus, int cost) {
            this.bus = bus;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()); // 도시 개수
        M = Integer.parseInt(br.readLine()); // 버스 개수

        // 최소 비용 저장
        minCost = new int[N+1];
        Arrays.fill(minCost, Integer.MAX_VALUE);

        // 버스의 비용 정보 저장
        connectList = new ArrayList[N+1];
        for(int i = 1; i <= N; i++) {
            connectList[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            connectList[from].add(new Point(to, cost));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken()); // 시작 도시
        int end = Integer.parseInt(st.nextToken()); // 도착 도시

        System.out.println(findCost(start, end));
    }

    public static int findCost(int start, int end) {
        PriorityQueue<Point> pque = new PriorityQueue<>((p1, p2) -> p1.cost - p2.cost);
        pque.offer(new Point(start, 0));
        minCost[start] = 0;

        while(!pque.isEmpty()) {
            Point from = pque.poll();
            if(from.bus == end) return from.cost;

            // from.bus랑 연결되어있는 도시
            for(Point to : connectList[from.bus]) {
                // from.bus에서 to.bus로 가는데 드는 총 비용
                int totalCost = from.cost + to.cost;
                if(totalCost < minCost[to.bus]) {
                    pque.offer(new Point(to.bus, totalCost));
                    minCost[to.bus] = totalCost;
                }
            }
        }
        return -1;
    }
}
