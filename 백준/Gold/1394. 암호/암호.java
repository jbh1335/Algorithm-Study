import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String password = br.readLine();
        long answer = 0;

        for(int i = 0; i < password.length(); i++) {
            // 현재 문자를 str에서 몇 번째 인덱스인지 찾기
            int idx = str.indexOf(password.charAt(i));
            // 이전에 만들 수 있는 횟수 + 마지막 문자 인덱스 순서
            answer = (answer * str.length() + (idx + 1)) % 900528;
        }
        System.out.println(answer);
    }
}
