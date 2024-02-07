import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long S = Long.valueOf(br.readLine());

        long N = 1, sum = 1;
        while(true) {
            sum += ++N;
            if(sum >= S) break;
        }

        if(sum > S) N--;
        System.out.println(N);
    }
}
