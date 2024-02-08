import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0, end = 0, answer = 0;
        long sum = arr[0];

        while(start <= end) {
            if(sum <= M) {
                if(sum == M) answer++;
                if(++end == arr.length) break;
                sum += arr[end];
            } else {
                if(start == end) {
                    if(++end == arr.length) break;
                    sum += arr[end];
                } else {
                    sum -= arr[start++];
                }
            }
        }
        System.out.println(answer);
    }
}
