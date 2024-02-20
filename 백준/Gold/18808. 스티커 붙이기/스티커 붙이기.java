import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K, count;
    static int[][] map;
    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int stickerN = Integer.parseInt(st.nextToken());
            int stickerM = Integer.parseInt(st.nextToken());
            int[][] sticker = new int[stickerN][stickerM];

            for(int a = 0; a < stickerN; a++) {
                st = new StringTokenizer(br.readLine());
                for(int b = 0; b < stickerM; b++) {
                    sticker[a][b] = Integer.parseInt(st.nextToken());
                }
            }

            // 0, 90, 180, 270도 회전
            boolean isAble = false;
            for(int r = 0; r < 4; r++) {
                // 스티커 붙이기
                loop:
                for(int a = 0; a <= N-sticker.length; a++) {
                    for(int b = 0; b <= M-sticker[0].length; b++) {
                        if(putSticker(a, b, sticker)) {
                            isAble = true;
                            break loop;
                        }
                    }
                }

                // 스티커 붙이기 실패했으면 회전
                if(!isAble) {
                    sticker = rotateSticker(sticker);
                } else {
                    break;
                }
            }
        }

        System.out.println(count);
    }

    // 스티커 붙일 수 있는지 확인
    public static boolean putSticker(int x, int y, int[][] sticker) {
        ArrayList<Point> list = new ArrayList<>();
        for(int i = 0; i < sticker.length; i++) {
            for(int j = 0; j < sticker[0].length; j++) {
                if(sticker[i][j] == 1) {
                    // 붙이려는 곳에 다른 스티커가 없으면 좌표 저장
                    if(map[i+x][j+y] == 0) list.add(new Point(i+x, j+y));
                        // 다른 스티커가 있으면 실패
                    else return false;
                }
            }
        }

        // 성공했으면 스티커 붙이기
        for(Point p : list) {
            map[p.x][p.y] = 1;
            count++;
        }
        return true;
    }

    // 시계방향으로 90도 회전
    public static int[][] rotateSticker(int[][] sticker) {
        int[][] arr = new int[sticker[0].length][sticker.length];
        int idxX = 0, idxY = 0;
        for(int j = 0; j < sticker[0].length; j++) {
            for(int i = sticker.length-1; i >= 0; i--) {
                arr[idxX][idxY++] = sticker[i][j];
            }
            idxX++;
            idxY = 0;
        }

        return arr;
    }
}
