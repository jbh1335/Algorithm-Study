import java.io.*;
import java.util.*;

public class Main {
    static int N, M, answer = Integer.MAX_VALUE;
    static int[][] map;
    static Point[] pickChicken;
    static ArrayList<Point> chickenList = new ArrayList<>();
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

        N = Integer.parseInt(st.nextToken()); // 행, 열
        M = Integer.parseInt(st.nextToken()); // 폐업시키지 않을 치킨집의 최대 수
        map = new int[N][N]; // N*N 배열
        pickChicken = new Point[M]; // M개의 치킨 뽑기

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) chickenList.add(new Point(i, j)); // 치킨집 저장
            }
        }

        com(0, 0);
        System.out.println(answer);
    }

    // 조합으로 치킨집 M개 뽑기
    public static void com(int cnt, int start) {
        if(cnt == M) {
            // 도시의 치킨 거리
            int distSum = 0;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(map[i][j] == 1) distSum += chickenDistance(i, j);
                }
            }
            // 도시의 치킨 거리의 최솟값 구하기
            answer = Math.min(answer, distSum);
            return;
        }

        for(int i = start; i < chickenList.size(); i++) {
            pickChicken[cnt] = chickenList.get(i);
            com(cnt+1, i+1);
        }
    }

    // 치킨 거리 구하기
    public static int chickenDistance(int x, int y) {
        int dist = Integer.MAX_VALUE;
        for(int i = 0; i < pickChicken.length; i++) {
            int newDist = Math.abs(x - pickChicken[i].x) + Math.abs(y - pickChicken[i].y);
            dist = Math.min(dist, newDist);
        }
        return dist;
    }
}
