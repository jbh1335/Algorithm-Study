import java.io.*;
import java.util.*;

/*
    길이가 N인 컨베이어 벨트가 있고 위아래 감싸며 돌고 있음 -> 총 길이: 2N
    1  2    ... N-1  N
    2N 2N-1 ... N+2  N+1
    1: 올리는 위치 (로봇은 여기서만 올릴 수 있음)
    N: 내리는 위치 (여기 오면 바로 로봇 사라짐, 내구도 영향X)

    아래와 같이 로봇을 옮긴다. 종료되었을 때 아래의 과정을 몇번 수행했는지 구하기
    1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전 -> 시계방향으로 이동
    2. 가장 먼저 올라간 로봇부터 한 칸 이동
        1) 이동하기 위해서는 이동하려는 칸에 로봇이 없고 그 칸의 내구도가 1이상 있어야함 -> 이동하면 그 칸의 내구도 -1
        2) 이동할 수 없으면 가만히 있음
    3. 올리는 위치(1번)의 내구도가 0이 아니면 로봇을 올림 -> 올리는 순간 내구도 -1
    4. 내구도가 0인 칸의 개수가 K개 이상이라면 종료, 아니면 1번부터 반복
 */
public class Main {
    static int N, K;
    static ArrayList<Point> conveyorList;
    static class Point {
        int num;
        boolean isRobot;
        public Point(int num, boolean isRobot) {
            this.num = num;
            this.isRobot = isRobot;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 길이가 N인 컨베이어 벨트
        K = Integer.parseInt(st.nextToken()); // 내구도가 0인 칸의 개수가 K개 이상

        conveyorList = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 2*N; i++) {
            conveyorList.add(new Point(Integer.parseInt(st.nextToken()), false));
        }

        int answer = 0;
        while(K > 0) {
            // 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전
            conveyorList.add(0, conveyorList.get(conveyorList.size()-1));
            conveyorList.remove(conveyorList.size()-1);
            // 회전했는데 내리는 위치에 로봇이 도달하면 제거
            if(conveyorList.get(N-1).isRobot) conveyorList.get(N-1).isRobot = false;

            // 로봇이 있는 곳은 한 칸 이동
            move();

            // 올리는 위치의 내구도가 0이 아니면
            Point first = conveyorList.get(0);
            if(first.num != 0) {
                // 로봇 올리고 내구도 감소
                if(--first.num == 0) K--;
                first.isRobot = true;
            }
            answer++;
        }
        System.out.println(answer);
    }

    public static void move() {
        // 가장 먼저 올라간 로봇부터 한 칸 이동
        for(int i = N-2; i >= 0; i--) {
            Point now = conveyorList.get(i);
            // 로봇이 있으면
            if(now.isRobot) {
                Point next = conveyorList.get(i+1);
                // 다음 칸에 로봇이 없고 그 칸의 내구도가 1이상 있으면 이동 가능
                if(!next.isRobot && next.num >= 1) {
                    // 원래 있던 위치에서 로봇 제거
                    now.isRobot = false;
                    // 이동한 칸의 내구도 감소하고 0이면 개수 세기
                    if(--next.num == 0) K--;
                    // 이동한 칸이 내리는 위치가 아니면 로봇 이동 (내리는 위치이면 로봇 사라짐)
                    if(i+1 != N-1) next.isRobot = true;
                }
            }
        }
    }
}
