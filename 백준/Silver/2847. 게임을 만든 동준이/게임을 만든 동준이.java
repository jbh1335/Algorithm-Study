import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int before = arr[N-1], answer = 0;
        for(int i = N-2; i >= 0; i--) {
            if(arr[i] >= before) {
                int diff = arr[i] - before + 1;
                answer += diff;
                before--;
            } else {
                before = arr[i];
            }
        }
        System.out.println(answer);
    }
}
