import java.io.*;
import java.util.*;

public class Main {
    static int H, W, answer;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new int[H][W];

        st = new StringTokenizer(br.readLine());
        for(int j = 0; j < W; j++) {
            int height = Integer.parseInt(st.nextToken());
            for(int i = H-1; i >= H-height; i--) {
                map[i][j] = 1;
            }
        }

        for(int j = 0; j < W; j++) {
            for(int i = 0; i < H; i++) {
                if(map[i][j] == 1) {
                    countRain(i, j);
                    break;
                }
            }
        }

        System.out.println(answer);
    }

    public static void countRain(int x, int y) {
        for(int i = x; i < H; i++) {
            int count = 0;
            for(int j = y+1; j < W; j++) {
                if(map[i][j] == 1) {
                    answer += count;
                    break;
                }
                count++;
            }
        }
    }
}
