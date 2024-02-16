import java.util.*;
class Solution {
    /*
        모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 구하기
        다리에 총 bridge_length개 만큼 올라갈 수 있음
        다리에 있는 트럭의 무게 <= weigth
        큐 = 다리
        큐에 트럭을 하나씩 넣고 조건 때문에 못들어가면 0 넣기
        1. 다리에 아무것도 없는 경우 -> 바로 들어가기 가능
        2. 다리에 트럭이 있는 경우
            1) bridge_length개 만큼 있음 -> 못들어감, 앞에 트럭 빼주기
            2) bridge_length개 만큼 없음
                기존 무게 + 현재 트럭 무게 <= weight 이면 들어감, 아니면 0으로 넣음
    */
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        
        int totalWeight = 0;
        Queue<Integer> que = new LinkedList<>();
        for(int i = 0; i < truck_weights.length; i++) {
            while(true) {
                if(que.isEmpty()) { // 다리에 아무것도 없으면
                    // 현재 트럭 넣어주고
                    que.offer(truck_weights[i]);
                    // 다리 위에 있는 트럭 무게 증가
                    totalWeight += truck_weights[i];
                    // 시간 증가
                    answer++;
                    break;
                } else { // 다리에 트럭 있음
                    // 최대 개수만큼 있음
                    if(que.size() == bridge_length) {
                        // 맨 앞 트럭 빼주고 그만큼 무게 감소
                        totalWeight -= que.poll();
                    } else {
                        // 기존 무게 + 현재 트럭 무게 <= weight
                        if(totalWeight + truck_weights[i] <= weight) {
                            // 무게가 가능하므로 트럭 넣어줌
                            que.offer(truck_weights[i]);
                            // 무게 증가
                            totalWeight += truck_weights[i];
                            // 시간 증가
                            answer++;
                            break;
                        } else { // 무게 때문에 못들어가면 0을 넣어줌
                            que.offer(0);
                            answer++;
                        }
                    }
                }
            }
        }
        // 다리 위에 있는 트럭들이 나오려면 걸리는 시간 추가
        answer += bridge_length;
        return answer;
    }
}