import java.io.*;
import java.util.*;

// 플로이드 와샬 알고리즘 -> 모든 정점에서 다른 모든 정점을 조회하면서 연결되어 있는지 확인
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] connect = new int[N][N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                connect[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // k를 지나서 i에서 j까지 갈 수 있는지 확인
        for(int k = 0; k < N; k++) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    // i -> k, k -> j가 성립하면 i -> j 가능
                    if(connect[i][k] == 1 && connect[k][j] == 1) {
                        connect[i][j] = 1;
                    }
                }
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                System.out.print(connect[i][j] + " ");
            }
            System.out.println();
        }
    }
}
