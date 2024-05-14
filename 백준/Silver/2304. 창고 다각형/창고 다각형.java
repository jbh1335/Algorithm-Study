import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int loc, height;
        boolean isUp;
        public Point(int loc, int height, boolean isUp) {
            this.loc = loc;
            this.height = height;
            this.isUp = isUp;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][2];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, (arr1, arr2) -> arr1[0] - arr2[0]);

        Stack<Point> stack = new Stack<>();
        for(int i = 0; i < N; i++) {
            if(stack.isEmpty()) {
                stack.push(new Point(arr[i][0], arr[i][1], true));
                continue;
            }

            // 전보다 현재의 높이가 더 높으면
            if(stack.peek().height < arr[i][1]) {
                // 오목하게 들어간 부분이 없도록 하향인 애들 제거 (상향은 높이가 낮아도 통과)
                while(true) {
                    Point before = stack.peek();
                    if(before.height < arr[i][1] && !before.isUp) {
                        stack.pop();
                    } else {
                        break;
                    }
                }
            }

            if(stack.peek().height < arr[i][1]) stack.push(new Point(arr[i][0], arr[i][1], true));
            else stack.push(new Point(arr[i][0], arr[i][1], false));
        }

        int answer = 0;
        int location = stack.peek().loc + 1;
        while(!stack.isEmpty()) {
            Point now = stack.pop();
            if(now.isUp) {
                answer += now.height * (location - now.loc);
                location = now.loc;
            } else {
                answer += now.height * (location - (stack.peek().loc+1));
                location = stack.peek().loc+1;
            }
        }
        System.out.println(answer);
    }
}
