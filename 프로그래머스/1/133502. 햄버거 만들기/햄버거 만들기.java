import java.util.*;
class Solution {
    public int solution(int[] ingredient) {
        int answer = 0;
        ArrayList<Integer> list = new ArrayList<>();
        
        for(int i = 0; i < ingredient.length; i++) {
            list.add(ingredient[i]);
            if(list.size() >= 4) {
                int first = list.get(list.size()-4);
                int second = list.get(list.size()-3);
                int third = list.get(list.size()-2);
                int fourth = list.get(list.size()-1);
            
                if(first == 1 && second == 2 && third == 3 && fourth == 1) {
                    for(int j = 0; j < 4; j++) {
                        list.remove(list.size()-1);
                    }
                    answer++;
                }
            }
        }
        return answer;
    }
}