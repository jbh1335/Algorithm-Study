import java.io.*;

public class Main {
    static int N;
    static String answer;
    static int[] arr = {1, 2, 3};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dfs(0, "");
        System.out.println(answer);
    }

    public static void dfs(int cnt, String strNum) {
        if(answer != null) return;
        if(cnt == N) {
            answer = strNum;
            return;
        }

        for(int i = 0; i < 3; i++) {
            if(checkOk(strNum+arr[i])) {
                dfs(cnt+1, strNum+arr[i]);
            }
        }
    }

    public static boolean checkOk(String strNum) {
        if(strNum.length() == 1) return true;

        // 1자부터 시작해서 인접한 곳에 동일한 부분 수열이 있는지 확인
        int end1 = 0, end2 = strNum.length();
        for(int i = 1; i <= strNum.length()/2; i++) {
            end1 = end2 - i;
            String left = strNum.substring(end1-i, end1);
            String right = strNum.substring(end2-i, end2);

            if(left.equals(right)) return false;
        }
        return true;
    }
}
