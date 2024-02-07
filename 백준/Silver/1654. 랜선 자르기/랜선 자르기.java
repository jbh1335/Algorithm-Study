import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] arr = new int[K];
        long start = 1, end = 0;
        for(int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            if(arr[i] > end) end = arr[i];
        }

        int answer = 0;
        while(start <= end) {
            long mid = (start + end) / 2;
            long sum = 0;

            for(int i : arr) {
                sum += i / mid;
            }

            if(sum >= N) {
                answer = (int) Math.max(answer, mid);
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        System.out.println(answer);
    }
}
