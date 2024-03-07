import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0, end = 0, max = 0;
        int count = arr[0] % 2 == 0 ? 0 : 1;
        while(start < N) {
            if(count <= K) {
                if(count == K) {
                    max = Math.max(max, end-start+1-K);
                }

                if(++end == N) {
                    max = Math.max(max, end-start-count);
                    break;
                }
                if(arr[end] % 2 == 1) count++;
            } else {
                if(arr[start] % 2 == 1) count--;
                start++;
            }
        }
        System.out.println(max);
    }
}
