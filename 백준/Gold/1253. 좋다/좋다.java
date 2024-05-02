import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        int answer = 0;

        for(int i = 0; i < N; i++) {
            int target = arr[i];
            int start = 0, end = N-1;

            while(start < end) {
                int sum = arr[start] + arr[end];

                if(sum == target) {
                    if(start == i) start++;
                    else if(end == i) end--;
                    else {
                        answer++;
                        break;
                    }
                } else if(sum < target) {
                    start++;
                } else {
                    end--;
                }
            }
        }
        System.out.println(answer);
    }
}
