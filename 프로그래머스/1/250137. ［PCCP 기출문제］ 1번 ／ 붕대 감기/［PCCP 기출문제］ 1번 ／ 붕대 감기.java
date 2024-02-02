class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int curHealth = health, maxTime = attacks[attacks.length-1][0];
        int count = 0, idx = 0;
        
        for(int i = 1; i <= maxTime; i++) {
            if(i == attacks[idx][0]) { // 공격받음
                curHealth -= attacks[idx][1];
                count = 0;
                idx++;
                // 체력 없으면 죽음
                if(curHealth <= 0) break;
            } else { // 체력 회복
                // 초당 회복량
                curHealth += bandage[1];
                if(count == bandage[0]) {
                    // 추가 회복량
                    curHealth += bandage[2];
                    count = 0;
                }
            }
            
            count++;
            if(curHealth > health) curHealth = health;
        }
        
        int answer = curHealth <= 0 ? -1 : curHealth;
        return answer;
    }
}