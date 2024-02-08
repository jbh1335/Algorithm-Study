import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] aArr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            aArr[i] = Integer.parseInt(st.nextToken());
        }

        int[] bArr = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            bArr[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        int idxA = 0, idxB = 0;
        for(int i = 0; i < N+M; i++) {
            if(idxA == N || idxB == M) break;

            if(aArr[idxA] < bArr[idxB]) sb.append(aArr[idxA++] + " ");
            else sb.append(bArr[idxB++] + " ");
        }

        for(int i = idxA; i < N; i++) {
            sb.append(aArr[i] + " ");
        }

        for(int i = idxB; i < M; i++) {
            sb.append(bArr[i] + " ");
        }
        System.out.println(sb);
    }
}
