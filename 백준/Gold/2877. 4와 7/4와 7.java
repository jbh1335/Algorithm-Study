import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        String[] arr = {"7", "4"};

        while(K > 0) {
            sb.append(arr[K%2]);
            if(K % 2 == 0) K--;
            K /= 2;
        }

        System.out.println(sb.reverse());
    }
}
