import java.io.*;
import java.util.*;

/*
    N*M 격자판에 적(1)과 빈칸(0)이 있음
    N+1번 행은 성이 있는데 궁수 3명을 배치할 수 있음 -> M명중 조합으로 3명 뽑기
    궁수는 거리가 D이하인 적들 중에 가장 가까운 적을 공격 (여럿일 경우 가장 왼쪽)
        -> 같은 적을 여러 궁수가 공격할 수 있음
    공격을 당한 적은 사라지고 나머지 적은 아래로 한 칸 이동함
    성이 있는 칸으로 적이 모두 이동하면 게임 끝
    궁수의 공격으로 제거할 수 있는 적의 최대 수 구하기
 */
public class Main {
    static int N, M, D, answer;
    static int[] select;
    static int[][] map, newMap;
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

        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        D = Integer.parseInt(st.nextToken()); // 공격 거리 제한

        map = new int[N][M]; // N*M 초기 격자판
        newMap = new int[N][M]; // N*M 격자판
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        select = new int[3];
        com(0, 0);
        System.out.println(answer);
    }

    // M개 중 궁수를 배치할 자리 3개 뽑기
    public static void com(int cnt, int start) {
        if(cnt == 3) {
            // 더이상 적이 없을 때까지 공격
            int count = 0;
            copyMap();

            while(checkEnemy()) {
                // 공격
                count += attack();
                // 공격 후 나머지 적은 아래로 이동
                moveDown();
            }

            answer = Math.max(answer, count);
            return;
        }

        for(int i = start; i < M; i++) {
            select[cnt] = i;
            com(cnt+1, i+1);
        }
    }

    // 공격
    public static int attack() {
        ArrayList<Point> attackList = new ArrayList<>();
        int count = 0;

        for(int archer : select) {
            PriorityQueue<Point> pque = new PriorityQueue<>((p1, p2) -> {
                if(p1.dist == p2.dist) return p1.y - p2.y;
                return p1.dist - p2.dist;
            });

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    // 적이 있으면
                    if(newMap[i][j] == 1) {
                        int dist = Math.abs(N - i) + Math.abs(archer - j);
                        if(dist <= D) pque.offer(new Point(i, j, dist));
                    }
                }
            }
            // 공격당하는 적들 저장
            if(!pque.isEmpty()) attackList.add(pque.poll());
        }

        // 공격
        for(Point p : attackList) {
            // 이미 공격한 적은 건너뜀
            if(newMap[p.x][p.y] == 0) continue;
            newMap[p.x][p.y] = 0;
            count++;
        }

        return count;
    }

    // 공격이 끝나면 나머지 적들 아래로 한 칸 이동
    public static void moveDown() {
        for(int j = 0; j < M; j++) {
            for(int i = N-1; i >= 0; i--) {
                if(i == 0) newMap[i][j] = 0;
                else newMap[i][j] = newMap[i-1][j];
            }
        }
    }

    // 적이 더 있는지 확인
    public static boolean checkEnemy() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(newMap[i][j] == 1) return true;
            }
        }
        return false;
    }

    // 초기 격자판을 만들기 위해 복사
    public static void copyMap() {
        for(int i = 0; i < N; i++) {
            newMap[i] = map[i].clone();
        }
    }
}
