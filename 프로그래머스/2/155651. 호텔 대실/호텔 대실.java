import java.util.*;
class Solution {
    public int solution(String[][] book_time) {
        PriorityQueue<Integer> pque = new PriorityQueue<>();
        int[][] bookTime = new int[book_time.length][2];
        
        for(int i = 0; i < book_time.length; i++) {
            String[] startArr = book_time[i][0].split(":");
            String[] endArr = book_time[i][1].split(":");
            
            bookTime[i][0] = Integer.parseInt(startArr[0]) * 60 + Integer.parseInt(startArr[1]);
            bookTime[i][1] = Integer.parseInt(endArr[0]) * 60 + Integer.parseInt(endArr[1]);
        }
        
        Arrays.sort(bookTime, (arr1, arr2) -> {
            if(arr1[0] == arr2[0]) return arr1[1] - arr2[1];
            return arr1[0] - arr2[0];
        });
        
        for(int[] arr : bookTime) {
            if(!pque.isEmpty() && pque.peek() + 10 <= arr[0]) pque.poll();
            pque.offer(arr[1]);
        }
        
        return pque.size();
    }
}