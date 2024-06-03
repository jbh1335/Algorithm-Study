import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int k = 0, length = 3;
        while(length < N) {
            length = length + (++k+3) + length;
        }

        String answer = "";
        N--;
        // 3등분하여 가운데 부분이 나올 때까지 찾기
        while(true) {
            // 가운데 부분 시작
            int midStart = (length - (k+3)) / 2;
            // 가운데 부분 끝
            int midEnd = midStart + (k+3) - 1;

            // 앞, 가운데, 끝 범위를 나누어서 검사
            if(N == midStart) { // 가운데 부분의 시작이면 m
                answer = "m";
                break;
            } else if(midStart < N && N <= midEnd) { // 그 이외는 o
                answer = "o";
                break;
            } else if(midEnd < N) { // 끝부분
                // 끝부분만 남기고 다 제거
                length -= (midEnd+1);
                N -= (midEnd+1);
            } else { // 앞부분
                // 앞부분만 남기고 다 제거
                length = midStart;
            }
            k--;
        }

        System.out.println(answer);
    }
}
