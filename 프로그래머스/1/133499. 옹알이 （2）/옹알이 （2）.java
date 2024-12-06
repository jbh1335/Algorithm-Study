class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        
        for(String str : babbling) {
            if(str.contains("ayaaya") || str.contains("yeye") || 
              str.contains("woowoo") || str.contains("mama")) continue;
            
            String newStr = str.replace("aya", "1").replace("ye", "2")
                                .replace("woo", "3").replace("ma", "4");
            
            newStr = newStr.replaceAll("[0-9]", "");
            if(newStr.equals("")) answer++;
        }
        
        return answer;
    }
}