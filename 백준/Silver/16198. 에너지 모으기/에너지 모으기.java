import java.io.*;
import java.util.*;

public class Main {
    static int N, answer;
    static ArrayList<Integer> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        list = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        dfs(0);
        System.out.println(answer);
    }

    public static void dfs(int sum) {
        if(list.size() == 2) {
            answer = Math.max(answer, sum);
            return;
        }

        for(int i = 1; i < list.size()-1; i++) {
            int target = list.get(i);
            int num = list.get(i-1) * list.get(i+1);

            list.remove(i);
            dfs(sum+num);
            list.add(i, target);
        }
    }
}
