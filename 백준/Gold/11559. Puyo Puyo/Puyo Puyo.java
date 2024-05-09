import java.io.*;
import java.util.*;

/*
    1. 뿌요는 아래에 바닥이나 다른 뿌요가 나올 때까지 아래로 떨어진다.
    2. 같은 색의 뿌요가 4개 이상 상하좌우로 연결되어 있으면 한꺼번에 없어진다. (1연쇄)
        -> 만약 사라질 그룹이 여러개 있다면 동시에 터져야 하고 1연쇄이다. (터지는 그룹마다 1연쇄 X)
    위 과정을 반복하여 연쇄가 몇 번 연속으로 일어날지 구하기
 */
public class Main {
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static class Point {
        int x, y;
        char color;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public Point(int x, int y, char color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new char[12][6];
        for(int i = 0; i < 12; i++) {
            String str = br.readLine();
            for(int j = 0; j < 6; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        int answer = 0;
        while(true) {
            // 같은 색의 뿌요들이 4개 이상 있는지 찾고 없애기
            boolean exist = false;
            for(int i = 0; i < 12; i++) {
                for(int j = 0; j < 6; j++) {
                    if(map[i][j] != '.') {
                        if(findGroup(i, j, map[i][j])) exist = true;
                    }
                }
            }

            // 사라질 뿌요가 없으면 끝내기
            if(!exist) break;

            // 뿌요 떨어짐
            fallDown();
            answer++;
        }
        System.out.println(answer);
    }

    // 같은 색의 뿌요들이 상하좌우로 있는지 찾기
    public static boolean findGroup(int x, int y, char target) {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(x, y));
        boolean[][] visited = new boolean[12][6];
        visited[x][y] = true;
        int count = 1;

        while(!que.isEmpty()) {
            Point p = que.poll();

            for(int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];

                if(nx >= 0 && ny >= 0 && nx < 12 && ny < 6) {
                    if(!visited[nx][ny] && map[nx][ny] == target) {
                        que.offer(new Point(nx, ny));
                        visited[nx][ny] = true;
                        count++;
                    }
                }
            }
        }

        // 같은 색의 뿌요가 4개 이상 있으면 사라짐
        if(count >= 4) {
            for(int i = 0; i < 12; i++) {
                for(int j = 0; j < 6; j++) {
                    if(visited[i][j]) map[i][j] = '.';
                }
            }
            return true;
        }
        return false;
    }

    // 뿌요가 아래로 떨어짐
    public static void fallDown() {
        // 아래에 바닥이나 다른 뿌요가 나올 때까지 아래로 떨어짐
        Stack<Point> stack = new Stack<>();
        for(int j = 0; j < 6; j++) {
            // 뿌요가 있으면 스택에 저장
            for(int i = 0; i < 12; i++) {
                if(map[i][j] != '.') stack.push(new Point(i, j, map[i][j]));
            }

            // 밑에서부터 빈칸이 있으면 스택에 있는 뿌요로 대체
            for(int i = 11; i >= 0; i--) {
                if(stack.isEmpty()) break;
                Point p = stack.pop();

                if(map[i][j] == '.') {
                    map[i][j] = p.color;
                    // 기존에 있던 뿌요는 아래로 떨어져서 사라짐
                    map[p.x][p.y] = '.';
                }
            }
        }
    }
}
