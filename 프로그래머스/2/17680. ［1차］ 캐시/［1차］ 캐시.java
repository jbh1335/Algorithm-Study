import java.util.*;
class Solution {
    public int solution(int cacheSize, String[] cities) {
        if(cacheSize == 0) return cities.length*5;
        Deque<String> deque = new ArrayDeque<>();
        deque.offerFirst(cities[0].toLowerCase());
        int answer = 5;
        
        for(int i = 1; i < cities.length; i++) {
            String city = cities[i].toLowerCase();
            
            if(deque.contains(city)) { // cache hit
                deque.remove(city);
                deque.offerFirst(city);
                answer++;
            } else { // cache miss
                if(deque.size() >= cacheSize) deque.pollLast();
                deque.offerFirst(city);
                answer += 5;
            }
        }
        
        return answer;
    }
}