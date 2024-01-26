import java.io.*;
import java.util.*;

/*
    9명의 선수가 야구를 N이닝 동안 게임 진행 -> 3아웃 발생하면 이닝 종료
    9번 타자까지 쳤는데 3아웃이 발생하지 않으면 이닝이 끝나지 않고 1번 타자가 다시 진행
    타순은 이닝이 변경되어도 순서 유지해야함 -> 2이닝에 6번이 타자였으면 3이닝에서는 7번부터 시작
    안타(1): 타자와 모든 주자가 한루씩 진루
    2루타(2): 두루씩, 3루타(3): 세루씩, 홈런(4): 모든 주자가 홈까지 진루
    아웃(0): 아무도 못감
    1번 선수는 4번 타자로 고정이고 다른 선수들의 타순을 정해야 하는데 가장 많은 득점을 할 수 있도록 구하기 (0 ~ 8번 선수로 풀기)
 */
public class Main {
    static int N, answer;
    static int[][] map;
    static int[] playersTurn;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][9]; // 각 선수가 각 이닝에서 얻는 결과를 저장하는 배열
        playersTurn = new int[9]; // 선수들 순서 저장하는 배열
        visited = new boolean[9]; // 방문배열

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        per(0, new int[8]);
        System.out.println(answer);
    }

    // 선수들의 순서를 순열로 뽑음 -> 0번 선수는 항상 4번째 순서이므로 제외하고 뽑음
    public static void per(int cnt, int[] select) {
        if(cnt == 8) {
            // playersTurn 배열에 0번 선수를 추가하여 저장
            for(int i = 0; i < 9; i++) {
                if(i == 3) continue;
                else if(i > 3) playersTurn[i] = select[i-1];
                else playersTurn[i] = select[i];
            }

            playGame();
            return;
        }

        for(int i = 1; i < 9; i++) {
            if(visited[i]) continue;

            select[cnt] = i;
            visited[i] = true;
            per(cnt+1, select);
            visited[i] = false;
        }
    }

    public static void playGame() {
        // 아웃 횟수, 이닝을 시작하는 선수, 총점수
        int out = 0, start = 0, totalScore = 0;
        // 전판이 몇번째 이닝인지, 현재가 몇번째 이닝인지
        int beforeInning = 0, inning = 0;
        // 경기장 (1루, 2루, 3루 상황)
        boolean[] base = new boolean[4];

        while(true) {
            // 이닝 수가 끝났으면 경기 끝
            if(inning == N) break;
            // 새로운 이닝이 아니면 다시 0번부터 시작
            // -> 새로운 이닝일 경우 전 이닝에서 끝난 선수의 다음 순서 선수가 이어서 진행해야함 (start)
            if(beforeInning == inning) start = 0;
            beforeInning = inning;

            for(int i = start; i < 9; i++) {
                int player = playersTurn[i]; // 현재 선수
                int score = map[inning][player]; // 선수가 득점한 점수

                if(score == 0) { // 아웃
                    if(++out == 3) {
                        // 다음 이닝으로 넘어감
                        inning++;
                        start = i == 8 ? 0 : i+1;
                        out = 0;
                        base = new boolean[4];
                        break;
                    }
                } else if(score == 1) { // 안타
                    // 3루에 있는 선수는 나오고
                    if(base[3]) {
                        base[3] = false;
                        totalScore++;
                    }
                    // 2루 선수가 3루로 이동
                    if(base[2]) {
                        base[3] = true;
                        base[2] = false;
                    }
                    // 1루 선수가 2루로 이동
                    if(base[1]) {
                        base[2] = true;
                        base[1] = false;
                    }
                    // 타자가 1루로 이동
                    base[1] = true;
                } else if(score == 2) { // 2루타
                    for(int j = 3; j >= 2; j--) {
                        if(base[j]) {
                            base[j] = false;
                            totalScore++;
                        }
                    }
                    if(base[1]) {
                        base[3] = true;
                        base[1] = false;
                    }
                    base[2] = true;
                } else if(score == 3) { // 3루타
                    for(int j = 3; j >= 1; j--) {
                        if(base[j]) {
                            base[j] = false;
                            totalScore++;
                        }
                    }
                    base[3] = true;
                } else { // 홈런
                    for(int j = 1; j <= 3; j++) {
                        if(base[j]) {
                            base[j] = false;
                            totalScore++;
                        }
                    }
                    totalScore++;
                }
            }
        }
        answer = Math.max(answer, totalScore);
    }
}
