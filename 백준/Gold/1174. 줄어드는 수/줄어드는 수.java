import java.io.*;
import java.util.*;

public class Main {
    static int N, count;
    static ArrayList<Long> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();

        for(int i = 1; i <= 10; i++) {
            dfs(0, 0, 0, i);
            if(count >= N) break;
        }

        if(list.size() < N) System.out.println(-1);
        else {
            Collections.sort(list);
            System.out.println(list.get(N-1));
        }
    }

    public static void dfs(int cnt, int start, long num, int length) {
        if(cnt == length) {
            count++;
            list.add(num);
        }

        for(int i = start; i <= 9; i++) {
            long newNum = num + (long) (Math.pow(10, cnt)) * i;
            dfs(cnt+1, i+1, newNum, length);
        }
    }
}
