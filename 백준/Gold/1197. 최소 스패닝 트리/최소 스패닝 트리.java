import java.io.*;
import java.util.*;

public class Main {
    static int V, E;
    static int[] parents;
    static int[][] connect;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken()); // 정점의 개수
        E = Integer.parseInt(st.nextToken()); // 간선의 개수

        // 간선의 정보 저장
        connect = new int[E][3];
        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            connect[i][0] = Integer.parseInt(st.nextToken()); // A
            connect[i][1] = Integer.parseInt(st.nextToken()); // B
            connect[i][2] = Integer.parseInt(st.nextToken()); // A와 B가 연결되는데 드는 비용
        }

        // 가중치를 기준으로 오름차순 정렬
        Arrays.sort(connect, (arr1, arr2) -> arr1[2] - arr2[2]);

        // 자기 자신을 부모로 만들어서 초기화
        parents = new int[V+1]; // 부모 저장 배열
        for(int i = 1; i <= V; i++) {
            parents[i] = i;
        }

        int count = 0, answer = 0;
        for(int i = 0; i < E; i++) {
            // 먼저 연결할 수 있는지 확인하기 위해 각각의 부모 가져옴
            int pi = findParent(connect[i][0]);
            int pj = findParent(connect[i][1]);

            // 연결이 가능하면 수행
            if(union(pi, pj)) {
                answer += connect[i][2]; // 최소비용 누적
                // 정점을 모두 연결했으면 그만두기
                if(++count == V-1) break;
            }
        }
        System.out.println(answer);
    }

    // 부모 합치기
    public static boolean union(int i, int j) {
        // i와 j의 부모를 가져옴
        int pi = findParent(i);
        int pj = findParent(j);

        // 서로 같으면 연결할 수 없음 (싸이클 발생)
        if(pi == pj) return false;
        // 다르면 가능하므로 부모를 합침 (작은 값을 부모로 선정)
        if(pi < pj) parents[pj] = pi;
        else parents[pi] = pj;
        return true;
    }

    // 부모 찾기
    public static int findParent(int x) {
        // 자기 자신이 부모라면 자기를 return
        if(parents[x] == x) return x;

        // 최고 부모를 찾을 때까지 반복
        parents[x] = findParent(parents[x]);
        return parents[x];
    }
}
