import java.util.*;
class Solution {
    static int min = Integer.MAX_VALUE;
    static boolean[] visited;
    static ArrayList<Integer> list;
    public int solution(int n, int[] weak, int[] dist) {
        int answer = -1;
        visited = new boolean[dist.length];
        list = new ArrayList<>();
        
        if(weak.length == 1) return 1;
        
        for(int i : weak) {
            list.add(i);
        }
        
        for(int i = 0; i < weak.length; i++) {
            int end = i == 0 ? weak[weak.length-1] : weak[i-1]+n;
            dfs(0, weak[i], end, dist);
            list.remove(0);
            list.add(weak[i]+n);
        }
        
        if(min != Integer.MAX_VALUE) answer = min;
        return answer;
    }
    
    public static void dfs(int cnt, int start, int end, int[] dist) {
        if(cnt > dist.length) return;
        
        if(start > end) {
            min = Math.min(min, cnt);
            return;
        }
        
        for(int i = 0; i < dist.length; i++) {
            if(visited[i]) continue;
            
            visited[i] = true;
            // 다음 시작점 찾기 -> 취약 지점 아닌 곳은 건너뛰기
            int next = findNext(start+dist[i]);
            dfs(cnt+1, next, end, dist);
            visited[i] = false;
        }
    }
    
    public static int findNext(int next) {
        // next보다 크고 가장 가까운 취약 지점 찾기
        for(int num : list) {
            if(next < num) return num;
        }
        return next+1;
    }
}