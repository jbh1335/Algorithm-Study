import java.io.*;
import java.util.*;

public class Main {
    static int R, C, answer;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<Point> personQue, fireQue;
    static class Point {
        int x, y, dist;
        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        personQue = new LinkedList<>();
        fireQue = new LinkedList<>();
        for(int i = 0; i < R; i++) {
            String str = br.readLine();
            for(int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'J') personQue.offer(new Point(i, j, 0));
                else if(map[i][j] == 'F') fireQue.offer(new Point(i, j, 0));
            }
        }

        while(true) {
            if(personQue.isEmpty()) break;
            personQue = move("person", personQue);
            if(answer > 0) break;
            fireQue = move("fire", fireQue);
        }

        if(answer > 0) System.out.println(answer);
        else System.out.println("IMPOSSIBLE");
    }

    public static Queue<Point> move(String type, Queue<Point> que) {
        int size = que.size();
        loop:
        for(int s = 0; s < size; s++) {
            Point p = que.poll();
            // 사람이 있던 곳이 불로 퍼졌으면 건너뜀
            if(type.equals("person") && map[p.x][p.y] == 'F') continue;

            for(int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];

                if(nx >= 0 && ny >= 0 && nx < R && ny < C) {
                    if(type.equals("person")) { // 사람이 이동하는 경우
                        if(map[nx][ny] == '.') {
                            que.offer(new Point(nx, ny, p.dist+1));
                            map[nx][ny] = 'J';
                        }
                    } else { // 불이 이동하는 경우
                        if(map[nx][ny] != '#' && map[nx][ny] != 'F') {
                            que.offer(new Point(nx, ny, p.dist+1));
                            map[nx][ny] = 'F';
                        }
                    }
                } else {
                    // 탈출 성공
                    if(type.equals("person")) {
                        answer = p.dist+1;
                        break loop;
                    }
                }
            }
        }

        return que;
    }
}
