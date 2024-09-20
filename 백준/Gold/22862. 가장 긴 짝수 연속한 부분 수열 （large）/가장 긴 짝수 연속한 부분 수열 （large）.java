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

        int start = 0, end = 0, answer = 0, count = 0;
        while(end < N) {
            if(arr[end] % 2 == 0) {
                answer = Math.max(answer, end-start+1-count);
                end++;
            } else {
                if(count < K) {
                    count++;
                    answer = Math.max(answer, end-start+1-count);
                    end++;
                } else {
                    if(arr[start++] % 2 == 1) count--;
                }
            }
        }

        System.out.println(answer);
    }
}
