import java.util.*;
class Solution {
    static String[] answer;
    static class Point {
        int cnt;
        String city;
        boolean[] visited;
        String[] route;
        public Point(String city, int cnt, boolean[] visited, String[] route) {
            this.city = city;
            this.cnt = cnt;
            this.visited = visited;
            this.route = route;
        }
    }
    public String[] solution(String[][] tickets) {
        Arrays.sort(tickets, (arr1, arr2) -> {
            if(arr1[0].equals(arr2[0])) return arr1[1].compareTo(arr2[1]);
            return arr1[0].compareTo(arr2[0]);
        });
        
        for(int i = 0; i < tickets.length; i++) {
            if(tickets[i][0].equals("ICN")) {
                if(bfs(i, tickets)) break;
            }
        }
        
        return answer;
    }
    
    public static boolean bfs(int idx, String[][] tickets) {
        Queue<Point> que = new LinkedList<>();
        boolean[] visited = new boolean[tickets.length];
        visited[idx] = true;
        String[] route = new String[tickets.length+1];
        route[0] = tickets[idx][0];
        route[1] = tickets[idx][1];
        que.offer(new Point(tickets[idx][1], 2, visited, route));
        
        while(!que.isEmpty()) {
            Point p = que.poll();
            if(p.cnt == route.length) {
                answer = p.route;
                return true;
            }
            
            for(int i = 0; i < tickets.length; i++) {
                if(!p.visited[i] && tickets[i][0].equals(p.city)) {
                    boolean[] newVisited = p.visited.clone();
                    newVisited[i] = true;
                    String[] newRoute = p.route.clone();
                    newRoute[p.cnt] = tickets[i][1];
                    
                    que.offer(new Point(tickets[i][1], p.cnt+1, newVisited, newRoute));
                }
            }
        }
        
        return false;
    }
}