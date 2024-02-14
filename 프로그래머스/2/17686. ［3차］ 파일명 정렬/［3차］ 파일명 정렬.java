import java.util.*;
class Solution {
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        
        // 각 파일마다 head, number, tail로 잘라서 저장하는 배열
        String[][] sArr = new String[files.length][3];
        for(int i = 0; i < files.length; i++) {
            String file = files[i];
            
            String number = "";
            int idx = 0; // number가 끝나는 인덱스
            for(int j = 0; j < file.length(); j++) {
                char ch = file.charAt(j);
                if(0+'0' <= ch && ch <= 9+'0') { // 숫자 찾기
                    number += ch;
                    idx = j;
                } else {
                    // 숫자를 다 찾았으면 끝내기
                    if(!number.equals("")) break;
                }
            }
            
            sArr[i][0] = file.substring(0, idx-number.length()+1); // head
            sArr[i][1] = number; // number
            sArr[i][2] = file.substring(idx+1, file.length()); // tail
        }
        
        Arrays.sort(sArr, (arr1, arr2) -> {
            String str1 = arr1[0].toLowerCase();
            String str2 = arr2[0].toLowerCase();
            if(str1.equals(str2)) {
                int num1 = Integer.parseInt(arr1[1]);
                int num2 = Integer.parseInt(arr2[1]);
                return num1 - num2;
            }
            return str1.compareTo(str2);
        });
        
        for(int i = 0; i < sArr.length; i++) {
            String str = "";
            for(int j = 0; j < 3; j++) {
                str += sArr[i][j];
            }
            answer[i] = str;
        }
        return answer;
    }
}