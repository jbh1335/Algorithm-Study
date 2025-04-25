import java.util.*;
class Solution {
    static class Point {
        String word;
        int cnt;
        
        public Point(String word, int cnt) {
            this.word = word;
            this.cnt = cnt;
        }
    }
    public int solution(String begin, String target, String[] words) {
        return bfs(begin, target, words);
    }
    
    public static int bfs(String begin, String target, String[] words) {
        Queue<Point> que = new LinkedList<>();
        boolean[] visited = new boolean[words.length];
        que.offer(new Point(begin, 0));
        
        while(!que.isEmpty()) {
            Point p = que.poll();
            
            for(int i = 0; i < words.length; i++) {
                if(!visited[i] && checkOneDifferent(p.word, words[i])) {
                    if(words[i].equals(target)) return p.cnt + 1;
                    que.offer(new Point(words[i], p.cnt+1));
                    visited[i] = true;
                }
            }
        }
        
        return 0;
    }
    
    public static boolean checkOneDifferent(String str1, String str2) {
        int count = 0;
        
        for(int i = 0; i < str1.length(); i++) {
            if(str1.charAt(i) != str2.charAt(i)) count++;
            if(count > 1) return false;
        }
        
        return true;
    }
}