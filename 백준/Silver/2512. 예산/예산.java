import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        int start = 0, end = 0, sum = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
            if(arr[i] > end) end = arr[i];
        }

        int M = Integer.parseInt(br.readLine());
        int answer = 0;

        if(sum <= M) answer = end;
        else {
            while(start <= end) {
                int mid = (start + end) / 2;
                sum = 0;

                for(int i : arr) {
                    if(i > mid) sum += mid;
                    else sum += i;
                }

                if(sum <= M) {
                    answer = Math.max(answer, mid);
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        System.out.println(answer);
    }
}
