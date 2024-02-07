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

        int M = Integer.parseInt(br.readLine());
        int[] findArr = new int[M];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            findArr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        for(int i = 0; i < M; i++) {
            int start = 0, end = N-1, mid = 0;
            int target = findArr[i];
            boolean exist = false;

            while(start <= end) {
                mid = (start + end) / 2;

                if(arr[mid] == target) {
                    exist = true;
                    break;
                } else if(arr[mid] < target) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }

            findArr[i] = exist ? 1 : 0;
        }

        for(int i : findArr) {
            System.out.print(i + " ");
        }
    }
}
