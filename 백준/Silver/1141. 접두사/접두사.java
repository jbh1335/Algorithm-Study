import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] arr = new String[N];

        for(int i = 0; i < N; i++) {
            arr[i] = br.readLine();
        }

        Arrays.sort(arr);
        int count = 1;
        // 연속된 2개 문자를 비교, 앞이 뒤의 접두사가 아니면 통과
        for(int i = 0; i < N-1; i++) {
            if(arr[i+1].startsWith(arr[i])) continue;
            count++;
        }
        System.out.println(count);
    }
}
