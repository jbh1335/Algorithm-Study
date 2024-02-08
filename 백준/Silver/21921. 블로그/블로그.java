import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        int sum = 0;
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if(i < X) sum += arr[i];
        }

        int max = sum, count = 1;
        for(int i = X; i < N; i++) {
            sum -= arr[i-X];
            sum += arr[i];

            if(sum > max) {
                max = sum;
                count = 1;
            } else if(sum == max) {
                count++;
            }
        }

        if(max == 0) System.out.println("SAD");
        else {
            System.out.println(max);
            System.out.println(count);
        }
    }
}
