import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] aArr = new int[N];
        int[] bArr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            aArr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            bArr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(aArr);
        Arrays.sort(bArr);

        int answer = 0;
        for(int i = 0; i < N; i++) {
            answer += aArr[i] * bArr[N-i-1];
        }
        System.out.println(answer);
    }
}
