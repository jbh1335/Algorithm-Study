import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            String[] arr = new String[N];

            for(int i = 0; i < N; i++) {
                arr[i] = br.readLine();
            }

            Arrays.sort(arr);
            String answer = "YES";
            for(int i = 1; i < N; i++) {
                if(arr[i].startsWith(arr[i-1])) {
                    answer = "NO";
                    break;
                }
            }
            System.out.println(answer);
        }
    }
}
