import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        int min = Integer.MAX_VALUE, answer1 = 0, answer2 = 0;
        int start = 0, end = N-1;
        while(start < end) {
            int sum = arr[start] + arr[end];
            int abs = Math.abs(arr[start] + arr[end]);
            
            if(abs < min) {
                min = abs;
                answer1 = Math.min(arr[start], arr[end]);
                answer2 = Math.max(arr[start], arr[end]);
            }

            if(sum == 0) break;
            else if (sum > 0) end--;
            else start++;
        }
        System.out.println(answer1 + " " + answer2);
    }
}
