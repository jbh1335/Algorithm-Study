import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        HashSet<Character> set = new HashSet<>();
        String[] option = new String[N]; // 옵션 저장
        int[] idxArr = new int[N]; // 각 옵션의 몇번째 글자가 단축키인지 저장
        Arrays.fill(idxArr, -1);

        for(int i = 0; i < N; i++) {
            option[i] = br.readLine();
        }

        for(int i = 0; i < N; i++) {
            String str = option[i].toLowerCase();
            String[] splitArr = str.split(" ");

            // 하나의 옵션에 대해 왼쪽부터 오른쪽 순서로 단어의 첫 글자가 단축키로 지정되었는지 확인
            int idx = 0;
            for(String word : splitArr) {
                // 아직 지정이 안되어있으면 단축키로 지정
                if(set.add(word.charAt(0))) {
                    idxArr[i] = idx;
                    break;
                }
                idx += word.length() + 1;
            }

            // 모든 단어의 첫 글자가 이미 지정이 되어있다면 왼쪽부터 차례대로 알파벳을 보면서 단축기로 지정
            if(idxArr[i] == -1) {
                for(int j = 0; j < str.length(); j++) {
                    if(str.charAt(j) == ' ') continue;
                    if(set.add(str.charAt(j))) {
                        idxArr[i] = j;
                        break;
                    }
                }
            }
        }

        for(int i = 0; i < N; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(option[i]);

            if(idxArr[i] != -1) {
                sb.insert(idxArr[i], "[");
                sb.insert(idxArr[i]+2, "]");
            }
            System.out.println(sb);
        }
    }
}
