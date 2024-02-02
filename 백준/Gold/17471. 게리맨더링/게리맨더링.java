import java.io.*;
import java.util.*;

public class Main {
    static int N, totalPeople, min = Integer.MAX_VALUE;
    static int[] people, select1, select2;
    static int[][] connect;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        people = new int[N+1];
        connect = new int[N+1][N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            people[i] = Integer.parseInt(st.nextToken());
            totalPeople += people[i];
        }

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            for(int j = 0; j < num; j++) {
                int x = Integer.parseInt(st.nextToken());
                connect[i][x] = connect[i][i] = 1;
            }
        }

        for(int i = 1; i <= N/2; i++) {
            select1 = new int[i];
            select2 = new int[N-i];
            com(0, 1, i, new boolean[N+1]);
        }

        if(min == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(min);
    }

    public static void com(int cnt, int start, int num, boolean[] selected) {
        if(cnt == num) {
            int idx = 0;
            for(int i = 1; i <= N; i++) {
                if(!selected[i]) select2[idx++] = i;
            }

            if(checkConnected(select1) && checkConnected(select2)) {
                int peopleNum1 = 0;
                for(int i : select1) {
                    peopleNum1 += people[i];
                }
                int peopleNum2 = totalPeople - peopleNum1;
                min = Math.min(min, Math.abs(peopleNum1 - peopleNum2));
            }
            return;
        }

        for(int i = start; i <= N; i++) {
            select1[cnt] = i;
            selected[i] = true;
            com(cnt+1, i+1, num, selected);
            selected[i] = false;
        }
    }

    // 뽑은 선거구가 서로 연결되어있는지 확인
    public static boolean checkConnected(int[] arr) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(arr[0]);
        boolean[] visited = new boolean[N+1];
        visited[arr[0]] = true;

        while(!que.isEmpty()) {
            int num = que.poll();

            for(int i = 0; i < arr.length; i++) {
                if(!visited[arr[i]] && connect[num][arr[i]] == 1) {
                    que.offer(arr[i]);
                    visited[arr[i]] = true;
                }
            }
        }

        for(int i = 0; i < arr.length; i++) {
            if(!visited[arr[i]]) return false;
        }
        return true;
    }
}
