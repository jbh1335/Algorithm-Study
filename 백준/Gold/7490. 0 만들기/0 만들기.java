import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static ArrayList<String> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            list = new ArrayList<>();

            dfs(2, "1");
            for(String str : list) {
                System.out.println(str);
            }
            System.out.println();
        }
    }

    public static void dfs(int cnt, String str) {
        if(cnt > N) {
            // 공백은 숫자를 이어 붙인것이므로 제거
            String newStr = str.replace(" ", "");
            // 연산자를 기준으로 잘라서 숫자만 남김
            String[] numArr = newStr.split("[+|-]");
            // 숫자를 기준으로 잘라서 연산자만 남김
            String[] opArr = newStr.split("[0-9]+");

            // 계산
            int num = Integer.parseInt(numArr[0]);
            for(int i = 1; i < opArr.length; i++) {
                if(opArr[i].equals("+")) num += Integer.parseInt(numArr[i]);
                else num -= Integer.parseInt(numArr[i]);
            }

            // 결과가 0이면 저장
            if(num == 0) list.add(str);
            return;
        }

        dfs(cnt+1, str + " " + cnt);
        dfs(cnt+1, str + "+" + cnt);
        dfs(cnt+1, str + "-" + cnt);
    }
}
