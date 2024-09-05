import java.util.*;
class Solution {
    public int[] solution(int[][] score) {
        int[] answer = new int[score.length];
        HashMap<Integer, Double> map = new HashMap<>();
        
        for(int i = 0; i < score.length; i++) {
            double avg = (score[i][0] + score[i][1]) / 2.0;
            map.put(i, avg);
        }
        
        ArrayList<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list, (o1, o2) -> Double.compare(map.get(o2), map.get(o1)));
        
        int rank = 1, count = 0;
        double before = 0;
        for(int idx : list) {
            if(before != map.get(idx)) {
                rank += count;
                count = 0;
            }
            
            answer[idx] = rank;
            before = map.get(idx);
            count++;
        }
        return answer;
    }
}