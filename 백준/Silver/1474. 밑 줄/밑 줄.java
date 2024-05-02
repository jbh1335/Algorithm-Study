import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static String underLine = "";
    static String[] arr;
    static ArrayList<String> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        arr = new String[N];
        int length = 0;
        for(int i = 0; i < N; i++) {
            arr[i] = br.readLine();
            length += arr[i].length();
        }

        M -= length;
        // 공통 밑줄
        int lineNum = M / (N-1);
        while(lineNum-- > 0) {
            underLine += "_";
        }

        M %= (N-1);
        // 남은 밑줄의 개수 채워서 사전순으로 빠른 단어 찾기
        String answer = "";
        if(M > 0) {
            com(0, 0, new boolean[N]);
            Collections.sort(list);
            answer = list.get(0);
        } else {
            for(int i = 0; i < N; i++) {
                answer += arr[i];
                if(i != N-1) answer += underLine;
            }
        }
        System.out.println(answer);
    }

    public static void com(int cnt, int start, boolean[] visited) {
        if(cnt == M) {
            String str = "";
            for(int i = 0; i < N; i++) {
                str += arr[i];
                if(i != N-1) str += underLine;
                if(visited[i]) str += "_";
            }

            list.add(str);
            return;
        }

        for(int i = start; i < N-1; i++) {
            visited[i] = true;
            com(cnt+1, i+1, visited);
            visited[i] = false;
        }
    }
}
