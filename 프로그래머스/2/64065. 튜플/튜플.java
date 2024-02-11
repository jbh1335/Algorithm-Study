import java.util.*;
class Solution {
    public int[] solution(String s) {
        s = s.substring(1, s.length()-2).replace("{", "");
        String[] splitArr = s.split("},");
        int[] answer = new int[splitArr.length];
        
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < splitArr.length; i++) {
            list.add(splitArr[i]);
        }
        
        Collections.sort(list, (o1, o2) -> o1.length() - o2.length());
        boolean[] visited = new boolean[100001];
        for(int i = 0; i < list.size(); i++) {
            String[] numArr = list.get(i).split(",");
            for(String sNum : numArr) {
                int num = Integer.parseInt(sNum);
                if(!visited[num]) {
                    answer[i] = num;
                    visited[num] = true;
                    break;
                }
            }
        }
        return answer;
    }
}