import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            String strNum = br.readLine();
            // 분모, 분자
            long denominator = 0, numerator = 0;

            int startIdx = strNum.indexOf('(');
            if(startIdx != -1) { // 반복되는 수가 있으면
                // 반복되는 수
                String repeat = strNum.substring(startIdx+1, strNum.length()-1);
                // 반복되는 수 전까지 있는 수 (처음부터 순환소수 나오기 전까지)
                String beforeRepeat = "";
                // 반복되는 수 포함 (처음부터 순환소수까지)
                String afterRepeat = "";

                if(startIdx == 2) {
                    beforeRepeat = "0";
                    afterRepeat = repeat;
                    denominator = (long) Math.pow(10, afterRepeat.length()) - 1;
                } else {
                    beforeRepeat = strNum.substring(2, startIdx);
                    afterRepeat = beforeRepeat + repeat;
                    // 자릿수만큼 10 곱함
                    denominator = (long) (Math.pow(10, afterRepeat.length()) - Math.pow(10, beforeRepeat.length()));
                }

                // 소수점 이후는 같으므로 정수부분 빼기
                numerator = Long.valueOf(afterRepeat) - Long.valueOf(beforeRepeat);
            } else { // 반복되는 수가 없을 때
                String str = strNum.substring(2, strNum.length());
                denominator = (long) Math.pow(10, str.length());
                numerator = Long.valueOf(str);
            }

            // 분모와 분자는 서로소이어야 하므로 최대공약수 찾기
            long gcd = gcd(denominator, numerator);
            denominator /= gcd;
            numerator /= gcd;

            System.out.println(numerator + "/" + denominator);
        }
    }

    public static long gcd(long a, long b) {
        if(b == 0) return a;
        return gcd(b, a % b);
    }
}
