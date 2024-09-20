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
        int start = 0, end = N-1, sum = 0, min = Integer.MAX_VALUE;
        int num1 = 0, num2 = 0;

        while(start < end) {
            sum = arr[start] + arr[end];

            if(Math.abs(sum) < min) {
                num1 = arr[start];
                num2 = arr[end];
                min = Math.abs(sum);
            }

            if(sum >= 0) end--;
            else start++;
        }

        System.out.println(num1 + " " + num2);
    }
}
