import java.io.*;

public class Main {
    static int N, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String sNum = String.valueOf(N);

        if(sNum.length() >= 3) {
            com(0, 1, countOdd(sNum), sNum, new int[2]);
        } else if(sNum.length() == 2) {
            twoLength(sNum, countOdd(sNum));
        } else {
            oneLength(sNum, 0);
        }

        System.out.println(min + " " + max);
    }

    public static void com(int cnt, int start, int count, String sNum, int[] select) {
        if(cnt == 2) {
            int a = Integer.parseInt(sNum.substring(0, select[0]));
            int b = Integer.parseInt(sNum.substring(select[0], select[1]));
            int c = Integer.parseInt(sNum.substring(select[1], sNum.length()));

            int num = a + b + c;
            String strNum = String.valueOf(num);
            if(strNum.length() != 1) count += countOdd(strNum);

            if(strNum.length() >= 3) { // 길이가 3이상 -> 다시 나눔
                com(0, 1, count, strNum, new int[2]);
            } else if(strNum.length() == 2) { // 길이가 2
                twoLength(strNum, count);
            } else { // 길이가 1
                oneLength(strNum, count);
            }
            return;
        }

        for(int i = start; i <= sNum.length()-1; i++) {
            select[cnt] = i;
            com(cnt+1, i+1, count, sNum, select);
        }
    }

    public static void twoLength(String strNum, int count) {
        while(true) {
            int x = strNum.charAt(0) - '0';
            int y = strNum.charAt(1) - '0';

            strNum = String.valueOf(x+y);
            if(strNum.length() == 1) {
                oneLength(strNum, count);
                break;
            }
            count += countOdd(strNum);
        }
    }

    public static void oneLength(String strNum, int count) {
        count += countOdd(strNum);
        max = Math.max(max, count);
        min = Math.min(min, count);
    }

    public static int countOdd(String strNum) {
        int count = 0;
        for(int i = 0; i < strNum.length(); i++) {
            int num = strNum.charAt(i) - '0';
            if(num % 2 == 1) count++;
        }
        return count;
    }
}
