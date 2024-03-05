import java.io.*;
import java.util.*;

/*
    2^N * 2^N 얼음판이 있다.
    map[x][y]가 0이면 얼음이 없다는 것이다.
    파이어스톰을 시전하려면 단게 L을 결정해야 한다.
    1. 격자를 2^L * 2^L 크기로 나눈다.
    2. 모든 부분 격자를 시계 방향으로 90도 회전시킨다.
    3. 상하좌우로 얼음이 있는 곳(0이 아닌)이 3개 미만이면 얼음의 양 -1
    마법사 상어가 파이어스톰을 총 Q번 시전하고 나서 남아있는 얼음의 합,
    남아있는 얼음 중 가장 큰 덩어리가 차지하는 칸의 개수를 구해라.
 */
public class Main {
    static int N, Q, length;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static ArrayList<Point> list;
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

        N = Integer.parseInt(st.nextToken()); // 2^N * 2^N
        Q = Integer.parseInt(st.nextToken()); // 총 몇번 시전하는지

        length = (int) Math.pow(2, N);
        map = new int[length][length];
        for(int i = 0; i < length; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < length; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 시전할 때마다 어떤 단계인지
        st = new StringTokenizer(br.readLine());
        for(int l = 0; l < Q; l++) {
            int L = Integer.parseInt(st.nextToken());
            // 1칸은 회전 X
            if(L > 0) rotate();

            // 칸마다 자리 이동
            firestorm((int) Math.pow(2, L));

            // 감소해야할 얼음 찾기
            list = new ArrayList<>();
            for(int i = 0; i < length; i++) {
                for(int j = 0; j < length; j++) {
                    if(map[i][j] > 0) findIce(i, j);
                }
            }

            // 얼음의 양 -1
            for(Point p : list) {
                map[p.x][p.y]--;
            }
        }

        visited = new boolean[length][length];
        int sum = 0, max = 0;
        for(int i = 0; i < length; i++) {
            for(int j = 0; j < length; j++) {
                sum += map[i][j];
                if(!visited[i][j] && map[i][j] > 0) max = Math.max(max, bfs(i, j));
            }
        }

        System.out.println(sum);
        System.out.println(max);
    }

    // 남아있는 얼음중 차지하는 칸의 개수 찾기
    public static int bfs(int x, int y) {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(x, y));
        visited[x][y] = true;
        int count = 1;

        while(!que.isEmpty()) {
            Point p = que.poll();

            for(int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];

                if(checkRange(nx, ny) && !visited[nx][ny] && map[nx][ny] > 0) {
                    que.offer(new Point(nx, ny));
                    visited[nx][ny] = true;
                    count++;
                }
            }
        }

        return count;
    }

    // 영역별로 자리 이동
    public static void firestorm(int size) {
        int[][] newMap = new int[length][length];
        int x = 0, y = 0;
        for(int j = length-size; j >= 0; j-=size) {
            for(int i = 0; i < length; i+=size) {
                x = length - j - size;
                for(int a = i; a < i+size; a++) {
                    y = i;
                    for(int b = j; b < j+size; b++) {
                        newMap[x][y++] = map[a][b];
                    }
                    x++;
                }
            }
        }

        copyMap(newMap);
    }

    // 인접한 곳에 얼음 찾기
    public static void findIce(int x, int y) {
        // 상하좌우로 얼음이 있는 곳(0이 아닌)이 3개 미만이면 얼음의 양 -1해야하기 때문에 좌표 list에 저장
        int count = 0;
        for(int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(checkRange(nx, ny) && map[nx][ny] > 0) {
                count++;
            }
        }

        if(count < 3) list.add(new Point(x, y));
    }

    // 전체를 시계방향으로 90도 회전
    public static void rotate() {
        int[][] newMap = new int[length][length];
        for(int j = 0; j < length; j++) {
            int idx = 0;
            for(int i = length-1; i >= 0; i--) {
                newMap[j][idx++] = map[i][j];
            }
        }

        copyMap(newMap);
    }

    // 배열 옮기기
    public static void copyMap(int[][] newMap) {
        for(int i = 0; i < length; i++) {
            map[i] = newMap[i].clone();
        }
    }

    // 범위 확인
    public static boolean checkRange(int nx, int ny) {
        if(nx >= 0 && ny >= 0 && nx < length && ny < length) return true;
        return false;
    }
}
