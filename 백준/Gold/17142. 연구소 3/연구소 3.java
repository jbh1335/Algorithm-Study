import java.io.*;
import java.util.*;

/*
    바이러스: 활성 & 비활성 상태
    처음에는 모두 비활성 상태임
    비활성 상태인 바이러스 중에 M개를 활성 상태로 변경해서 모든 칸에 퍼뜨릴 수 있는 최소의 시간은?
    (1초마다 상하좌우로 퍼짐)
 */
public class Main {
    static int N, M, notVirusSize, notVirus, count, min;
    static int[][] graph, newGraph;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Point[] pickVirus;
    static ArrayList<Point> virusList = new ArrayList<Point>();
    static Queue<Point> que = new ArrayDeque<>();
    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // N*N
        M = Integer.parseInt(st.nextToken()); // 놓을 바이러스 개수 M

        graph = new int[N][N];
        newGraph = new int[N][N];
        pickVirus = new Point[M];
        min = Integer.MAX_VALUE;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if(graph[i][j] == 2) virusList.add(new Point(i, j));
                if(graph[i][j] == 0) notVirusSize++;
            }
        }

        if(notVirusSize > 0) {
            com(0, 0);
            if(min == Integer.MAX_VALUE) System.out.println("-1");
            else System.out.println(min);
        } else {
            System.out.println("0");
        }
    }

    public static void com(int cnt, int start) {
        if(cnt == M) {
            makeNewGraph();
            for(int i = 0; i < M; i++) {
                int x = pickVirus[i].x;
                int y = pickVirus[i].y;
                que.add(new Point(x, y));
                newGraph[x][y] = -1;
            }

            notVirus = notVirusSize;
            bfs();
            return;
        }

        for(int i = start; i < virusList.size(); i++) {
            pickVirus[cnt] = virusList.get(i);
            com(cnt+1, i+1);
        }
    }

    public static void bfs() {
        count = 0;

        while(!que.isEmpty()) {
            int size = que.size();

            for(int s = 0; s < size; s++) {
                Point p = que.poll();

                for(int i = 0; i < 4; i++) {
                    int nx = p.x + dx[i];
                    int ny = p.y + dy[i];

                    if(nx >= 0 && ny >= 0 && nx < N && ny < N) {
                        if(newGraph[nx][ny] != -1 && newGraph[nx][ny] != 1) {
                            if(newGraph[nx][ny] == 0) notVirus--;
                            que.add(new Point(nx, ny));
                            newGraph[nx][ny] = -1;
                        }
                    }
                }
            }

            count++;
            if(notVirus == 0) {
                min = Math.min(min, count);
                que.clear();
                break;
            }
        }
    }

    public static boolean checkVirus() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(newGraph[i][j] == 0) return false;
            }
        }
        return true;
    }
    public static void makeNewGraph() {
        for(int i = 0; i < N; i++) {
            newGraph[i] = graph[i].clone();
        }
    }
}
