import java.io.*;
import java.util.*;

/*
    N*N 격자판에 검은색(-1), 무지개(0), 일반(1~M) 블록이 있다.
    블록 그룹: 2개 이상의 블록이 연결된 집합 (상하좌우로 연결가능한 모든 블록)
    일반 블록이 적어도 하나 있어야 하며 색이 모두 같아야함
    검은색은 포함 X, 무지개는 얼마나 있든 상관없음
    블록 그룹의 기준 -> 무지개가 아닌 블록 중 행의 번호가 가장 작은, 여러개면 열의 번호가 가장 작은 블록임

    오토 플레이: 다음과 같은 과정을 블록 그룹이 존재하는 동안 계속 반복
    1. 크기가 가장 큰 블록 그룹을 찾음
        여러개일 경우 다음과 같은 순으로 찾음
        1) 포함된 무지개 블록의 수가 가장 많은 경우
        2) 기준 블록의 행이 가장 큰 경우
        3) 열이 가장 큰 경우
    2. 1에서 찾은 블록 그룹을 모두 제거함 -> 블록의 수가 B개 이면 B^2점 획득
    3. 격자에 중력이 적용 -> 검은색을 제외한 모든 블록이 아래로 이동
    4. 격자가 90도 반시계 방향으로 회전
    5. 다시 격자에 중력이 적용
    오토 플레이가 모두 끝났을 때 획득한 점수의 합 구하기
 */
public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static class Point {
        int x, y, count, rainbow;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public Point(int x, int y, int count, int rainbow) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.rainbow = rainbow;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // N*N
        M = Integer.parseInt(st.nextToken()); // 색상의 개수

        map = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        // 오토 플레이: 블록 그룹이 존재하는 동안 계속 반복
        while(true) {
            ArrayList<Point> blockGroupList = new ArrayList<>();
            visited = new boolean[N][N];
            // 크기가 가장 큰 블록 그룹 찾기
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(!visited[i][j] && map[i][j] > 0) {
                        // arr[0]은 블록의 총 개수, arr[1]은 무지개 개수
                        int[] arr = findBlockGroup(i, j, map[i][j]);
                        // 블록의 개수가 2개 이상이면 추가
                        if(arr[0] >= 2) blockGroupList.add(new Point(i, j, arr[0], arr[1]));
                        // 무지개는 방문 해제
                        updateVisited();
                    }
                }
            }

            // 더 이상 블록 그룹이 없으면 끝내기
            if(blockGroupList.size() == 0) break;

            // 조건에 맞게 정렬
            Collections.sort(blockGroupList, (p1, p2) -> {
                if(p1.count == p2.count) {
                    if(p1.rainbow == p2.rainbow) {
                        if(p1.x == p2.x) return p2.y - p1.y;
                        return p2.x - p1.x;
                    }
                    return p2.rainbow - p1.rainbow;
                }
                return p2.count - p1.count;
            });

            // 찾은 블록 그룹을 모두 제거함
            Point blockGroup = blockGroupList.get(0);
            deleteBlockGroup(blockGroup.x, blockGroup.y, map[blockGroup.x][blockGroup.y]);
            // B^2점 획득
            answer += (blockGroup.count * blockGroup.count);

            // 격자에 중력이 적용 -> 검은색을 제외한 모든 블록이 아래로 이동
            gravity();
            // 격자가 90도 반시계 방향으로 회전
            rotate();
            // 다시 격자에 중력이 적용
            gravity();
        }

        System.out.println(answer);
    }

    // 반시계 방향으로 90도 회전
    public static void rotate() {
        int[][] newMap = new int[N][N];
        for(int i = 0; i < N; i++) {
            newMap[i] = map[i].clone();
        }

        for(int i = 0; i < N; i++) {
            Stack<Integer> stack = new Stack<>();
            for(int j = 0; j < N; j++) {
                stack.push(newMap[i][j]);
            }
            for(int j = 0; j < N; j++) {
                map[j][i] = stack.pop();
            }
        }
    }

    // 중력 적용
    public static void gravity() {
        for(int j = 0; j < N; j++) {
            for(int i = N-1; i >= 0; i--) {
                // 빈공간이면 가장 가까운 위 블록을 내림
                if(map[i][j] == -9) {
                    int idx = i;
                    while(idx-- > 0) {
                        // 검은색이면 멈춤
                        if(map[idx][j] == -1) break;
                        // 블록 내림
                        if(map[idx][j] >= 0) {
                            map[i][j] = map[idx][j];
                            map[idx][j] = -9;
                            break;
                        }
                    }
                }
            }
        }
    }

    // 블록 그룹 제거
    public static void deleteBlockGroup(int x, int y, int color) {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(x, y));
        // 블록 제거 (빈칸)
        map[x][y] = -9;

        while(!que.isEmpty()) {
            Point p = que.poll();

            for(int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];

                if(nx >= 0 && ny >= 0 && nx < N && ny < N) {
                    if(map[nx][ny] == color || map[nx][ny] == 0) {
                        que.offer(new Point(nx, ny));
                        map[nx][ny] = -9;
                    }
                }
            }
        }
    }

    // 블록 그룹 찾기
    public static int[] findBlockGroup(int x, int y, int color) {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(x, y));
        visited[x][y] = true;
        int count = 1, rainbow = 0;

        while(!que.isEmpty()) {
            Point p = que.poll();

            for(int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];

                if(nx >= 0 && ny >= 0 && nx < N && ny < N) {
                    // 방문한 적 없고 일반색 또는 무지개일 경우
                    if(!visited[nx][ny] && (map[nx][ny] == color || map[nx][ny] == 0)) {
                        que.offer(new Point(nx, ny));
                        visited[nx][ny] = true;
                        count++;
                        if(map[nx][ny] == 0) rainbow++;
                    }
                }
            }
        }

        return new int[] {count, rainbow};
    }

    // 무지개 블록은 방문 체크를 해제
    public static void updateVisited() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(visited[i][j] && map[i][j] == 0) visited[i][j] = false;
            }
        }
    }
}
