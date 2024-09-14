class Solution {
    public String solution(String polynomial) {
        String answer = "";
        String[] splitArr = polynomial.split(" \\+ ");
        int x = 0, num = 0;
        
        for(String str : splitArr) {
            if(str.contains("x")) {
                if(str.length() == 1) x++;
                else x += Integer.parseInt(str.substring(0, str.length()-1));
            } else {
                num += Integer.parseInt(str);
            }
        }
        
        if(x > 0) {
            if(x == 1) answer += "x";
            else answer += x + "x";
            
            if(num > 0) answer += " + " + num;
        } else {
            if(num > 0) answer += num;
        }
        
        return answer;
    }
}