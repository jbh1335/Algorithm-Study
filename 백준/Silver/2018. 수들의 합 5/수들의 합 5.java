import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int start = 1, end = 1, sum = 1, answer = 0;
        while(start <= end) {
            if(sum >= N) {
                if(sum == N) answer++;
                sum -= start++;
            } else {
                sum += ++end;
            }
        }
        System.out.println(answer);
    }
}
