import java.util.*;
class Solution {
    static boolean isFound;
    static String[] answer;
    static boolean[] visited;
    public String[] solution(String[][] tickets) {
        Arrays.sort(tickets, (arr1, arr2) -> {
            if(arr1[0].equals(arr2[0])) return arr1[1].compareTo(arr2[1]);
            return arr1[0].compareTo(arr2[0]);
        });
        
        answer = new String[tickets.length+1];
        visited = new boolean[tickets.length];
        // 출발지점 찾기
        for(int i = 0; i < tickets.length; i++) {
            if(tickets[i][0].equals("ICN")) {
                answer[0] = tickets[i][0];
                answer[1] = tickets[i][1];
                visited[i] = true;
                dfs(0, tickets[i][1], tickets);
                visited[i] = false;
                if(isFound) break;
            }
        }
        
        return answer;
    }
    
    public static void dfs(int cnt, String start, String[][] tickets) {
        if(isFound) return;
        if(cnt == tickets.length-1) {
            // 조건에 맞게 찾음
            if(!answer[answer.length-1].equals("")) isFound = true;
            return;
        }
        
        for(int i = 0; i < tickets.length; i++) {
            if(isFound) return;
            if(!visited[i] && start.equals(tickets[i][0])) {
                answer[cnt+2] = tickets[i][1];
                visited[i] = true;
                dfs(cnt+1, tickets[i][1], tickets);
                visited[i] = false;
            }
        }
    }
}