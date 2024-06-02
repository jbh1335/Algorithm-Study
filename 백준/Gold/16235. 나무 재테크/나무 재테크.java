import java.io.*;
import java.util.*;

/*
    N*N 땅에 M개의 나무를 심었다. -> 모든 칸에 양분이 5만큼 들어있음
    <봄>
    1. 나무가 자신의 나이만큼 양분을 먹고 나이가 1 증가함
        -> 각각의 나무는 자신의 칸에 있는 양분만 먹을 수 있음
    2. 하나의 칸에 여러 개의 나무가 있다면 나이가 어린 나무부터 양분을 먹음
    3. 만약 땅에 양분이 부족해 자신의 나이만큼 먹을 수 없다면 그 나무는 죽음
    <여름>
    1. 봄에 죽은 나무가 양분으로 변함
        -> 죽은 나무가 있던 칸에 (나이/2)만큼 양분으로 추가됨 (소수점 아래 버림)
    <가을>
    1. 나무가 번식함
        -> 번식하는 나무는 나이가 5의 배수이어야 하며, 인접한 8개의 칸에 나이가 1인 나무가 생김
    <겨울>
    1. 모든 땅에 양분 추가됨 (입력으로 주어짐)
        -> newNutrient[r][c]의 양만큼 추가됨

    K년이 지난 후 땅에 살아있는 나무의 개수 구하기
 */
public class Main {
    static int N, M, K;
    static int[][] newNutrient, nutrient;
    static ArrayList<Integer>[][] treeList;
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // N*N
        M = Integer.parseInt(st.nextToken()); // 나무 수
        K = Integer.parseInt(st.nextToken()); // K년 후

        // 1년마다 추가되는 양분
        newNutrient = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                newNutrient[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 현재 칸마다 있는 양분
        nutrient = new int[N][N];
        for(int i = 0; i < N; i++) {
            Arrays.fill(nutrient[i], 5);
        }

        // 각 칸마다 있는 나무 (한칸에 여러개 있을 수 있음)
        treeList = new ArrayList[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                treeList[i][j] = new ArrayList<>();
            }
        }

        // 초기에 있는 나무
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            treeList[r][c].add(age);
        }

        // K년 동안 과정 반복
        while(K-- > 0) {
            growUp();
            addTree();
            addNutrient();
        }

        // 남은 나무의 수 구하기
        int answer = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                answer += treeList[i][j].size();
            }
        }

        System.out.println(answer);
    }

    // 나무가 양분을 먹고 나이 증가
    public static void growUp() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                // 나무가 없으면 넘어감
                if(treeList[i][j].size() == 0) continue;
                // 나무가 죽으면 추가되는 양분
                int nutrientNum = 0;

                // 하나의 칸에 여러 개의 나무가 있다면 나이가 어린 나무부터 양분을 먹음
                for(int k = 0; k < treeList[i][j].size(); k++) {
                    int age = treeList[i][j].get(k);

                    if(nutrient[i][j] >= age) { // 자기 나이만큼 양분이 있으면
                        // 양분 먹고 나이가 1 증가함
                        nutrient[i][j] -= age;
                        treeList[i][j].set(k, age+1);
                    } else { // 죽음
                        // 여름: 죽은 나무가 있던 칸에 (나이/2)만큼 양분으로 추가됨 (소수점 아래 버림)
                        nutrientNum += (age / 2);
                        // 나무 제거
                        treeList[i][j].remove(k);
                        k--;
                    }
                }

                // 죽은 나무의 양분 추가
                nutrient[i][j] += nutrientNum;
            }
        }
    }

    // 나무 번식
    public static void addTree() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                for(int age : treeList[i][j]) {
                    // 나이가 5의 배수이면
                    if(age % 5 == 0) {
                        // 인접한 8개의 칸에 나이가 1인 나무가 생김
                        for(int d = 0; d < 8; d++) {
                            int nx = i + dx[d];
                            int ny = j + dy[d];

                            if(nx >= 0 && ny >= 0 && nx < N && ny < N) {
                                // 1살 나무는 가장 어리므로 0번째 인덱스에 추가
                                treeList[nx][ny].add(0, 1);
                            }
                        }
                    }
                }
            }
        }
    }

    // 양분 추가
    public static void addNutrient() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                nutrient[i][j] += newNutrient[i][j];
            }
        }
    }
}
