import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            int startIdx = BinarySearch(left, "left");
            int endIdx = BinarySearch(right, "right");

            System.out.println(endIdx - startIdx + 1);
        }
    }

    public static int BinarySearch(int target, String type) {
        int start = 0, end = N-1;

        while(start <= end) {
            int mid = (start + end) / 2;

            if(arr[mid] == target) {
                return mid;
            } else if(arr[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        if(type.equals("left")) return start;
        else return end;
    }
}
