import java.util.*;
class Solution {
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        
        // 각 파일마다 head, number, tail로 잘라서 저장하는 배열
        String[][] sArr = new String[files.length][3];
        for(int i = 0; i < files.length; i++) {
            String file = files[i];
            // 숫자가 나오면 split해서 head부분 저장
            String head = file.split("[0-9]")[0];
            // head를 제외한 나머지 부분
            String numberTail = file.substring(head.length(), file.length());
            // 숫자가 아닌 문자가 나오면 자르고 number부분 저장
            String number = numberTail.split("[^0-9]")[0];
            // head와 number를 제외한 tail부분 저장
            String tail = file.substring(head.length()+number.length(), file.length());
            
            sArr[i][0] = head;
            sArr[i][1] = number;
            sArr[i][2] = tail;
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