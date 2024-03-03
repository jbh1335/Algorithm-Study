import java.util.*;
class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        ArrayList<Integer> list = new ArrayList<>();
        
        for(int i = 0; i < operations.length; i++) {
            String[] splitArr = operations[i].split(" ");
            
            if(splitArr[0].equals("I")) {
                list.add(Integer.parseInt(splitArr[1]));
            } else {
                if(list.isEmpty()) continue;
                if(splitArr[1].equals("1")) {
                    list.remove(list.size()-1);
                } else {
                    list.remove(0);
                }
            }
            Collections.sort(list);
        }
        
        if(list.isEmpty()) {
            answer[0] = 0;
            answer[1] = 0;
        } else {
            answer[0] = list.get(list.size()-1);
            answer[1] = list.get(0);
        }
        return answer;
    }
}