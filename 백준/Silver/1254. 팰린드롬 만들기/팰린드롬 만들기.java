import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int answer = str.length();

        for(int i = 0; i < str.length()-1; i++) {
            int start = i;
            int end = str.length() - 1;
            boolean isAble = true;

            // 앞뒤가 같은지 검사
            while(start < end) {
                if(str.charAt(start++) != str.charAt(end--)) {
                    isAble = false;
                    break;
                }
            }

            // 찾았으면 끝내기
            if(isAble) {
                answer += i;
                break;
            }

            // 마지막까지 갔는데 없으면
            if(i == str.length()-2) {
                answer += answer - 1;
            }
        }

        System.out.println(answer);
    }
}
