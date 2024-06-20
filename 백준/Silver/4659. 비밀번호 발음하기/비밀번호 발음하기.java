import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            String str = br.readLine();
            if(str.equals("end")) break;

            char before = '.';
            int vowel = 0, consonant = 0;
            boolean isVowel = false, isAble = true;
            for(int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                // 모음과 자음 판별
                if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                    // 모음 하나는 반드시 포함하여야함
                    isVowel = true;
                    vowel++;
                    consonant = 0;
                } else {
                    consonant++;
                    vowel = 0;
                }

                // 모음이나 자음이 3개 연속으로 오면 안됨
                if(vowel == 3 || consonant == 3) {
                    isAble = false;
                    break;
                }

                // 같은 글자가 연속적으로 2번 나오면 안됨 (ee와 oo는 허용)
                if(i > 0 && before == ch) {
                    if(ch != 'e' && ch != 'o') {
                        isAble = false;
                        break;
                    }
                }

                before = ch;
            }

            if(isVowel && isAble) System.out.println("<" + str + "> is acceptable.");
            else System.out.println("<" + str + "> is not acceptable.");
        }
    }
}
