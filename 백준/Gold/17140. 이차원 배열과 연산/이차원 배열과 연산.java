import java.io.*;
import java.util.*;

public class Main {
    static int r, c, k, N = 3, M = 3;
    static int[][] arr, newArr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[100][100];
        for(int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // R연산: N >= M인 경우 모든 행의 크기를 가장 큰 행으로 변경
        // C연산: N < M인 경우 모든 열의 크기를 가장 큰 열로 변경
        int count = 0;
        if(arr[r-1][c-1] != k) {
            while(count++ < 100) {
                if(N >= M) sortByType('N');
                else sortByType('M');

                if(arr[r-1][c-1] == k) break;
            }
        }

        if(count > 100) count = -1;
        System.out.println(count);
    }

    // type를 기준으로 정렬
    public static void sortByType(char type) {
        // N은 행, M은 열을 기준으로 정렬, max는 각 행/열의 최댓값
        int end1 = N, end2 = M, max = 0;
        if(type == 'M') {
            end1 = M;
            end2 = N;
        }

        newArr = new int[100][100];
        for(int i = 0; i < end1; i++) {
            HashMap<Integer, Integer> map = new HashMap<>();
            for(int j = 0; j < end2; j++) {
                if(type == 'N') {
                    if(arr[i][j] == 0) continue;
                    map.put(arr[i][j], map.getOrDefault(arr[i][j], 0) + 1);
                } else {
                    if(arr[j][i] == 0) continue;
                    map.put(arr[j][i], map.getOrDefault(arr[j][i], 0) + 1);
                }
            }

            ArrayList<Integer> list = new ArrayList<>(map.keySet());
            Collections.sort(list, (o1, o2) -> map.get(o1) == map.get(o2) ? o1 - o2 : map.get(o1) - map.get(o2));

            int idx = -1;
            for(int num : list) {
                // 숫자
                if(++idx == 100) break;
                if(type == 'N') newArr[i][idx] = num;
                else newArr[idx][i] = num;

                // 개수
                if(++idx == 100) break;
                if(type == 'N') newArr[i][idx] = map.get(num);
                else newArr[idx][i] = map.get(num);
            }

            max = Math.max(max, list.size()*2);
            if(max > 100) max = 100;
        }

        if(type == 'N') M = max;
        else N = max;

        copyArr();
    }

    public static void copyArr() {
        for(int i = 0; i < N; i++) {
            arr[i] = newArr[i].clone();
        }
    }
}
