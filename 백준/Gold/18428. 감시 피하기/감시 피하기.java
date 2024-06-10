import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static String answer = "NO";
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static ArrayList<Point> emptyList;
    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new char[N][N];
        emptyList = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = st.nextToken().charAt(0);
                if(map[i][j] == 'X') emptyList.add(new Point(i, j));
            }
        }

        com(0, 0);
        System.out.println(answer);
    }

    public static void com(int cnt, int start) {
        if(answer.equals("YES")) return;
        if(cnt == 3) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(map[i][j] == 'T') {
                        if(!checkOk(i, j)) return;
                    }
                }
            }

            answer = "YES";
            return;
        }

        for(int i = start; i < emptyList.size(); i++) {
            Point p = emptyList.get(i);
            map[p.x][p.y] = 'O';
            com(cnt+1, i+1);
            map[p.x][p.y] = 'X';
        }
    }

    public static boolean checkOk(int teacherX, int teacherY) {
        boolean isAble = true;
        for(int d = 0; d < 4; d++) {
            int x = teacherX, y = teacherY;
            while(true) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if(nx >= 0 && ny >= 0 && nx < N && ny < N) {
                    if(map[nx][ny] == 'S') {
                        isAble = false;
                        break;
                    } else if(map[nx][ny] == 'O') break;
                } else {
                    break;
                }

                x = nx;
                y = ny;
            }
            if(!isAble) return false;
        }
        return true;
    }
}
