import java.util.*;
class Solution {
    public int[] solution(String s) {
        s = s.substring(1, s.length()-2).replace("{", "");
        String[] splitArr = s.split("},");
        int[] answer = new int[splitArr.length];
        
        Arrays.sort(splitArr, (o1, o2) -> o1.length() - o2.length());
        HashSet<Integer> set = new HashSet<>();
        
        for(int i = 0; i < splitArr.length; i++) {
            String[] numArr = splitArr[i].split(",");
            for(String sNum : numArr) {
                int num = Integer.parseInt(sNum);
                if(set.add(num)) {
                    answer[i] = num;
                    break;
                }
            }
        }
        return answer;
    }
}