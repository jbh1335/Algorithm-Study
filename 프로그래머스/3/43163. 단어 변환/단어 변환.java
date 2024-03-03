import java.util.*;
class Solution {
    static class Point {
        String word;
        int dist;
        boolean[] visited;
        public Point(String word, int dist, boolean[] visited) {
            this.word = word;
            this.dist = dist;
            this.visited = visited;
        }
    }
    public int solution(String begin, String target, String[] words) {
        int answer = bfs(begin, target, words);
        return answer;
    }
    
    public static int bfs(String begin, String target, String[] words) {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(begin, 0, new boolean[words.length]));
        
        while(!que.isEmpty()) {
            Point p = que.poll();
            
            for(int i = 0; i < words.length; i++) {
                if(!p.visited[i] && checkOneDiff(p.word, words[i])) {
                    if(words[i].equals(target)) return p.dist+1;
                    
                    p.visited[i] = true;
                    que.offer(new Point(words[i], p.dist+1, p.visited));
                }
            }
        }
        return 0;
    }
    
    public static boolean checkOneDiff(String str1, String str2) {
        int count = 0;
        for(int i = 0; i < str1.length(); i++) {
            if(str1.charAt(i) != str2.charAt(i)) count++;
            if(count > 1) return false;
        }
        return true;
    }
}