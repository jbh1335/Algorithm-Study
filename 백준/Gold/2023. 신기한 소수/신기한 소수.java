import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static ArrayList<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // N자리 수
        int[] leftNum = {2, 3, 5, 7};

        for(int i : leftNum) {
            makeNum(1, String.valueOf(i));
        }

        for(int i : list) {
            System.out.println(i);
        }
    }

    // 조건에 맞는 N의 자리 소수 찾기
    public static void makeNum(int cnt, String num) {
        if(cnt == N) {
            list.add(Integer.parseInt(num));
            return;
        }

        for(int i = 1; i < 10; i+=2) {
            if(i == 5) continue;

            if(checkOk(num+i)) {
                makeNum(cnt+1, num+i);
            }
        }
    }

    // 소수인지 확인
    public static boolean checkOk(String strNum) {
        int num = Integer.parseInt(strNum);
        int sqrt = (int) Math.sqrt(num);

        for(int i = 2; i <= sqrt; i++) {
            if(num % i == 0) return false;
        }
        return true;
    }
}
