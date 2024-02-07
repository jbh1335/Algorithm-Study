import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        long start = 1, end = 0;
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            if(arr[i] > end) end = arr[i];
        }

        end *= M; // 입국 심사를 마칠 수 있는 최대 시간 저장
        long answer = Long.MAX_VALUE;
        while(start <= end) {
            long mid = (start + end) / 2;
            long sum = 0;

            // 몇명이 심사를 마칠 수 있는지 구하기
            for(int i : arr) {
                sum += mid / i;
                if(sum >= M) break;
            }

            if(sum >= M) {
                answer = Math.min(answer, mid);
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        System.out.println(answer);
    }
}
