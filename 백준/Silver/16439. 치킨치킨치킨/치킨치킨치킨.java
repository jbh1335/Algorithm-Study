import java.io.*;
import java.util.*;

public class Main {
    static int N, M, answer;
    static int[][] chicken;
    static int[] select;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        chicken = new int[N][M];
        select = new int[3];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                chicken[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        com(0, 0);
        System.out.println(answer);
    }

    public static void com(int cnt, int start) {
        if(cnt == 3) {
            int sum = 0;
            for(int i = 0; i < N; i++) {
                int max = 0;
                for(int j = 0; j < 3; j++) {
                    max = Math.max(max, chicken[i][select[j]]);
                }
                sum += max;
            }
            answer = Math.max(answer, sum);
            return;
        }

        for(int i = start; i < M; i++) {
            select[cnt] = i;
            com(cnt+1, i+1);
        }
    }
}
