import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 앞 탑들의 인덱스를 저장
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++) {
            boolean flag = false;
            // 더 이상 검사할 탑이 없을 때까지 반복
            while(!stack.isEmpty()) {
                int idx = stack.peek();
                if(arr[idx] > arr[i]) { // 자신보다 높은 탑이 존재하면 그만두기
                    sb.append(idx + " ");
                    flag = true;
                    break;
                } else { // 자신보다 낮은 탑은 필요 없으므로 제거
                    stack.pop();
                }
            }
            // 레이저 수신할 탑 없으면 0 저장
            if(!flag) sb.append(0 + " ");
            // 자기 자신 넣기
            stack.push(i);
        }
        System.out.println(sb);
    }
}
