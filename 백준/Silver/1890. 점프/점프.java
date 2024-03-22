import java.io.*;
import java.util.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dx = {0, 1};
        int[] dy = {1, 0};
        long[][] dp = new long[N][N];
        dp[0][0] = 1;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(arr[i][j] == 0) continue;
                if(dp[i][j] > 0) {
                    int jump = arr[i][j];
                    for(int d = 0; d < 2; d++) {
                        int nx = i + dx[d]*jump;
                        int ny = j + dy[d]*jump;

                        if(checkRange(nx, ny)) {
                            dp[nx][ny] += dp[i][j];
                        }
                    }
                }
            }
        }
        System.out.println(dp[N-1][N-1]);
    }

    public static boolean checkRange(int nx, int ny) {
        if(nx >= 0 && ny >= 0 && nx < N && ny < N) return true;
        return false;
    }
}
