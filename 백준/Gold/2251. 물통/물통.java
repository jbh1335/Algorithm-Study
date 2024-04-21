import java.io.*;
import java.util.*;

public class Main {
    static int[] bucket;
    static boolean[][][] visited;
    static HashSet<Integer> set;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        bucket = new int[3];
        for(int i = 0; i < 3; i++) {
            bucket[i] = Integer.parseInt(st.nextToken());
        }

        // a, b, c의 물통 용량의 방문 배열
        visited = new boolean[bucket[0]+1][bucket[1]+1][bucket[2]+1];
        // 초기에는 c에만 가득 차있으므로 저장
        visited[0][0][bucket[2]] = true;
        set = new HashSet<>();

        bfs();
        int[] answer = new int[set.size()];
        int idx = 0;
        for(int i : set) {
            answer[idx++] = i;
        }

        Arrays.sort(answer);
        StringBuilder sb = new StringBuilder();
        for(int i : answer) {
            sb.append(i + " ");
        }
        System.out.println(sb);
    }

    public static void bfs() {
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[] {0, 0, bucket[2]});

        while(!que.isEmpty()) {
            int[] arr = que.poll();
            // a가 비어있으면 c 물의 양 저장
            if(arr[0] == 0) set.add(arr[2]);

            for(int i = 0; i < 3; i++) {
                if(arr[i] == 0) continue;
                for(int j = 0; j < 3; j++) {
                    if(i == j) continue;

                    // j 물통에 채울 수 있는 물의 양
                    int left = bucket[j] - arr[j];
                    // arr[j]가 가득차서 이동할 수 없으면 넘어감
                    if(left == 0) continue;
                    int[] newArr = arr.clone();
                    // arr[i]에 있는 물을 arr[j]에 모두 이동할 수 있으면 이동
                    if(left >= newArr[i]) {
                        newArr[j] += newArr[i];
                        newArr[i] = 0;
                    } else { // 아니면 arr[j]가 가득 찰때까지만 이동
                        newArr[j] += left;
                        newArr[i] -= left;
                    }

                    // 방문한 적 없으면 저장
                    if(!visited[newArr[0]][newArr[1]][newArr[2]]) {
                        que.offer(newArr);
                        visited[newArr[0]][newArr[1]][newArr[2]] = true;
                    }
                }
            }
        }
    }
}
