import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] score = new int[N][4];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            score[i][0] = Integer.parseInt(st.nextToken());
            score[i][1] = Integer.parseInt(st.nextToken());
            score[i][2] = Integer.parseInt(st.nextToken());
            score[i][3] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(score, (arr1, arr2) -> {
            if(arr1[1] == arr2[1]) {
                if(arr1[2] == arr2[2]) {
                    return arr2[3] - arr1[3];
                }
                return arr2[2] - arr1[2];
            }
            return arr2[1] - arr1[1];
        });

        int rank = 1, count = 0;
        for(int i = 0; i < N-1; i++) {
            if(score[i][0] == K) break;

            if(score[i][1] == score[i+1][1] && score[i][2] == score[i+1][2]
                    && score[i][3] == score[i+1][3]) {
                count++;
                continue;
            }

            rank++;
            rank += count;
            count = 0;
        }

        System.out.println(rank);
    }
}
