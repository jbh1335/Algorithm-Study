class Solution {
    public String solution(String[] id_pw, String[][] db) {
        String answer = "fail";
        
        for(String[] info : db) {
            if(id_pw[0].equals(info[0])) {
                answer = "wrong pw";
                if(id_pw[1].equals(info[1])) answer = "login";
            }
        }
        
        return answer;
    }
}