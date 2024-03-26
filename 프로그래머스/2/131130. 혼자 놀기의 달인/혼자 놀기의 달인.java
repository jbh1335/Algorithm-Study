import java.util.*;
class Solution {
    static boolean[] visited;
    public int solution(int[] cards) {
        int answer = 0;
        visited = new boolean[cards.length];
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < cards.length; i++) {
            if(!visited[i]) {
                int num = bfs(i, cards);
                if(num != -1) list.add(num);
            }
        }
        
        if(list.size() > 1) {
            Collections.sort(list, Collections.reverseOrder());
            answer = list.get(0) * list.get(1);
        }
        
        return answer;
    }
    
    public static int bfs(int start, int[] cards) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(start);
        visited[start] = true;
        int count = 1;
        
        while(!que.isEmpty()) {
            int x = que.poll();
            
            if(!visited[cards[x]-1]) {
                visited[cards[x]-1] = true;
                que.offer(cards[x]-1);
                count++;
            } else {
                return count;
            }
        }
        return -1;
    }
}