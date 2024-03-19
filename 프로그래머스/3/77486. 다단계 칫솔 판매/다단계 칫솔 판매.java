import java.util.*;
class Solution {
    /*
        자신이 판매한 이익에서 10%는 추천인에게 배분
        자신이 추천해서 받은 추가 이익의 10%도 추천인에게 배분 -> 여러명일 경우 각각 10%
        10%를 계산한 금액이 1원 미만이면 분배하지 않고 자신이 모두 갖음
    */
    static HashMap<String, Integer> payMap;
    static HashMap<String, String> referralMap;
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        // 각 직원의 수익 누적하여 저장
        payMap = new HashMap<>();
        // 추천인을 저장
        referralMap = new HashMap<>();
        
        for(int i = 0; i < enroll.length; i++) {
            payMap.put(enroll[i], 0); // 초기 수익은 0
            referralMap.put(enroll[i], referral[i]); // 추천인 저장
        }
        
        // 수익 계산
        for(int i = 0; i < seller.length; i++) {
            distribution(seller[i], amount[i]*100);
        }
        
        int[] answer = new int[enroll.length];
        for(int i = 0; i < enroll.length; i++) {
            answer[i] = payMap.get(enroll[i]);
        }
        return answer;
    }
    
    // 추천인이 있을 때까지 계속 분배
    public static void distribution(String me, int amount) {
        if(me.equals("-") || amount < 1) return;
        // 10% 계산
        int tenPer = (int) (amount * 0.1);
        // 나의 수익 저장
        payMap.put(me, payMap.getOrDefault(me, 0) + (amount-tenPer));
        // 나의 추천인에게 반복
        distribution(referralMap.get(me), tenPer);
    }
};