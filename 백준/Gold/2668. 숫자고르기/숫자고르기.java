import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];

        for(int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 1; i <= N; i++) {
            if(bfs(i)) {
                for(int j = 1; j <= N; j++) {
                    if(visited[j]) {
                        if(!list.contains(j)) list.add(j);
                    }
                }
            }
        }

        Collections.sort(list);
        System.out.println(list.size());
        for(int i : list) {
            System.out.println(i);
        }
    }

    public static boolean bfs(int num) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(num);
        visited = new boolean[N+1];
        visited[num] = true;

        while(!que.isEmpty()) {
            int x = que.poll();

            if(arr[x] == num) return true;
            if(!visited[arr[x]]) {
                visited[arr[x]] = true;
                que.offer(arr[x]);
            } else {
                break;
            }
        }
        return false;
    }
}
