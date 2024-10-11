import java.util.*;
class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        ArrayList<Integer> list = new ArrayList<>();
        
        for(String oper : operations) {
            String[] splitArr = oper.split(" ");
            
            if(splitArr[0].equals("I")) {
                list.add(Integer.parseInt(splitArr[1]));
                Collections.sort(list);
            } else {
                if(list.size() == 0) continue;
                
                if(splitArr[1].equals("1")) {
                    list.remove(list.size()-1);
                } else {
                    list.remove(0);
                }
            }
        }
        
        if(list.size() > 0) {
            answer[0] = list.get(list.size()-1);
            answer[1] = list.get(0);
        }
        
        return answer;
    }
}