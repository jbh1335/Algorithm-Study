import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int findNum = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N]; // N*N 배열
        int findNumIdxX = 0, findNumIdxY = 0; // findNum이 위치하는 좌표 출력해야함
        int[] dx = {1, 0, -1, 0}; // 하우상좌
        int[] dy = {0, 1, 0, -1};

        int num = N*N, idxX = 0, idxY = 0;
        map[0][0] = num--;
        while(true) {
            if(num <= 0) break;
            for(int i = 0; i < 4; i++) {
                if(num <= 0) break;
                while(true) {
                    int nx = idxX + dx[i];
                    int ny = idxY + dy[i];

                    if(nx >= 0 && ny >= 0 && nx < N && ny < N) {
                        // 이미 저장된 숫자라면 끝
                        if(map[nx][ny] > 0) break;
                        map[nx][ny] = num--;
                        idxX = nx;
                        idxY = ny;
                    } else { // 범위 벗어나면 방향 변경
                        break;
                    }
                }
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                // findNum의 좌표 찾기
                if(map[i][j] == findNum) {
                    findNumIdxX = i+1;
                    findNumIdxY = j+1;
                }
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }
        sb.append(findNumIdxX + " " + findNumIdxY);
        System.out.println(sb.toString());
    }
}
