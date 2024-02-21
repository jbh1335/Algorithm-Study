import java.util.*;
class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        // 시간을 분으로 변경하여 int형으로 예약 정보 저장
        int[][] bookArr = new int[book_time.length][2];
        
        for(int i = 0; i < book_time.length; i++) {
            for(int j = 0; j < 2; j++) {
                String[] splitArr = book_time[i][j].split(":");
                // 시간을 분으로 변경
                int hour = Integer.parseInt(splitArr[0]);
                int min = Integer.parseInt(splitArr[1]);
                min += hour * 60;
                
                // 대실 종료 시각은 청소 시간 10분 포함해서 저장
                if(j == 1) min += 10;
                bookArr[i][j] = min;
            }
        }
        
        // 대실 시작 시간을 기준으로 오름차순 정렬
        // 시작 시간이 같다면 종료 시간을 기준으로 오름차순 정렬
        Arrays.sort(bookArr, (arr1, arr2) -> {
            if(arr1[0] == arr2[0]) return arr1[1] - arr2[1];
            return arr1[0] - arr2[0];
        });
        
        // 대실 종료 시각을 저장하여 오름차순 정렬
        PriorityQueue<Integer> pque = new PriorityQueue<>();
        for(int i = 0; i < bookArr.length; i++) {
            if(pque.isEmpty()) {
                pque.offer(bookArr[i][1]);
                answer++;
                continue;
            }
            
            // 현재 사용중인 방중에 종료된 방이 있으면 그방에 들어감
            if(pque.peek() <= bookArr[i][0]) pque.poll();
            else answer++;
            
            pque.offer(bookArr[i][1]);
        }
        return answer;
    }
}