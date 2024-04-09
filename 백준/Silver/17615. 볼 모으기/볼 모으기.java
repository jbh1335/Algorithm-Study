import java.io.*;

public class Main {
    static int N;
    static String ball;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ball = br.readLine();

        int answer = Math.min(moveRight('R'), moveRight('B'));
        answer = Math.min(answer, Math.min(moveLeft('R'), moveLeft('B')));
        System.out.println(answer);
    }

    // type색을 오른쪽으로 이동
    public static int moveRight(char type) {
        boolean first = true;
        int count = 0;
        for(int i = ball.length()-1; i >= 0; i--) {
            // 처음에 type이 오른쪽에 있을 경우 넘어가기
            if(first && ball.charAt(i) == type) continue;
            else first = false;

            if(ball.charAt(i) == type) count++;
        }
        return count;
    }

    // type색을 왼쪽으로 이동
    public static int moveLeft(char type) {
        boolean first = true;
        int count = 0;
        for(int i = 0; i < ball.length(); i++) {
            if(first && ball.charAt(i) == type) continue;
            else first = false;

            if(ball.charAt(i) == type) count++;
        }
        return count;
    }
}
