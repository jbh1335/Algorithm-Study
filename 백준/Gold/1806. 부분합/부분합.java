import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0, end = 0, sum = arr[0];
        int answer = Integer.MAX_VALUE;
        while(end < N) {
            if(sum >= S) {
                answer = Math.min(answer, end-start+1);
                sum -= arr[start++];
            } else {
                if(++end == N) break;
                sum += arr[end];
            }
        }
        if(answer == Integer.MAX_VALUE) answer = 0;
        System.out.println(answer);
    }
}
