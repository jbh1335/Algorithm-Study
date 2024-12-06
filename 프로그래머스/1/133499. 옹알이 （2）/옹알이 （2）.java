class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        
        for(String str : babbling) {
            if(str.contains("ayaaya") || str.contains("yeye") || 
              str.contains("woowoo") || str.contains("mama")) continue;
            
            String newStr = str.replace("aya", " ").replace("ye", " ")
                                .replace("woo", " ").replace("ma", " ");
            
            newStr = newStr.replace(" ", "");
            if(newStr.length() == 0) answer++;
        }
        
        return answer;
    }
}