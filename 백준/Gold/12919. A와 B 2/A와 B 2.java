import java.io.*;

/*
    S에서 조건에 맞게 문자를 하나씩 추가해서 T가 될 때가지 반복하면 시간초과남
    그래서 반대로 T로 시작해서 조건에 맞게 문자를 하나씩 제거해서 S가 될 때까지 반복해야함
 */
public class Main {
    static boolean isFound;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();

        dfs(S, T);
        if(isFound) System.out.println(1);
        else System.out.println(0);
    }

    public static void dfs(String S, String T) {
        if(isFound) return;
        if(S.length() == T.length()) {
            if(S.equals(T)) isFound = true;
            return;
        }

        // 맨뒤가 A일 경우 A 제거
        if(T.charAt(T.length()-1) == 'A') {
            dfs(S, T.substring(0, T.length()-1));
        }

        // 맨앞이 B일 경우 B 제거하고 뒤집음
        if(T.charAt(0) == 'B') {
            StringBuilder sb = new StringBuilder();
            sb.append(T.substring(1, T.length()));
            sb.reverse();
            dfs(S, sb.toString());
        }
    }
}
