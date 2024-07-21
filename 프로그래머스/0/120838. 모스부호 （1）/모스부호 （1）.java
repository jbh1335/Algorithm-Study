class Solution {
    public String solution(String letter) {
        String answer = "";
        String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", 
                          "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", 
                          "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", 
                          "-.--", "--.."};
        
        String[] splitLetter = letter.split(" ");
        for(String str : splitLetter) {
            char ch = 'a';
            for(String m : morse) {
                if(str.equals(m)) {
                    answer += ch;
                    break;
                }
                ch++;
            }
        }
        
        return answer;
    }
}