import java.util.*;
class Solution {
    static int totalPicks, answer = Integer.MAX_VALUE;
    static int[] select;
    static int[][] tiredness = {{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};
    public int solution(int[] picks, String[] minerals) {
        // picks 배열은 dia, iron, stone 순서
        // 0: dia, 1: iron, 2: stone
        
        // 총 곡괭이 개수
        for(int i : picks) {
            totalPicks += i;
        }
        select = new int[totalPicks];
        
        per(0, picks, minerals);
        return answer;
    }
    
    // 곡괭이 순서 순열로 뽑기
    public static void per(int cnt, int[] picks, String[] minerals) {
        if(cnt == totalPicks) {
            answer = Math.min(answer, mining(minerals));
            return;
        }
        
        for(int i = 0; i < 3; i++) {
            if(picks[i] > 0) {
                select[cnt] = i;
                picks[i]--;
                per(cnt+1, picks, minerals);
                picks[i]++;
            }
        }
    }
    
    // 광물 캐기
    public static int mining(String[] minerals) {
        // 곡괭이 종류 인덱스
        int idx = 0;
        // 피로도 합
        int sum = 0;
        // 5개를 연속으로 캐야함
        int count = 0;
        
        for(int i = 0; i < minerals.length; i++) {
            if(idx == totalPicks) break;
            int target = 0;
            if(minerals[i].equals("diamond")) {
                target = 0;
            } else if(minerals[i].equals("iron")) {
                target = 1;
            } else {
                target = 2;
            }
            
            // 피로도 누적
            sum += tiredness[select[idx]][target];
            // 5번 사용했으면
            if(++count == 5) {
                // 다음 곡괭이로 넘어감
                idx++;
                count = 0;
            }
        }
        return sum;
    }
}