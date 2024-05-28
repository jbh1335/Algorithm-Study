import java.io.*;
import java.util.*;

public class Main {
    static int N, L, R, X, answer;
    static int[] level, select;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        level = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            level[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(level);
        for(int i = 2; i <= N; i++) {
            select = new int[i];
            com(0, 0, i);
        }

        System.out.println(answer);
    }

    public static void com(int cnt, int start, int num) {
        if(cnt == num) {
            // 가장 어려운 문제와 쉬운 문제의 난이도 차이는 X보다 크거나 같아야 함
            if(select[num-1] - select[0] >= X) {
                int sum = 0;
                for(int i : select) {
                    sum += i;
                }
                // 문제 난이도의 합이 조건에 맞으면 개수 세기
                if(L <= sum && sum <= R) answer++;
            }
            return;
        }

        for(int i = start; i < N; i++) {
            select[cnt] = level[i];
            com(cnt+1, i+1, num);
        }
    }
}
