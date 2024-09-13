import java.util.*;
class Solution {
    static String[] numArr = {"0", "5"};
    static ArrayList<Integer> list = new ArrayList<>();
    
    public int[] solution(int l, int r) {
        dfs("", l, r);
        if(list.size() == 0) return new int[] {-1};
        
        int[] answer = new int[list.size()];
        for(int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }
        
        Arrays.sort(answer);
        return answer;
    }
    
    public static void dfs(String strNum, int l, int r) {
        for(int i = 0; i < 2; i++) {
            int newNum = Integer.parseInt(strNum + numArr[i]);
            if(newNum == 0) continue;
            
            if(l <= newNum && newNum <= r) list.add(newNum);
            
            if(newNum <= r) dfs(strNum + numArr[i], l, r);
        }
    }
}