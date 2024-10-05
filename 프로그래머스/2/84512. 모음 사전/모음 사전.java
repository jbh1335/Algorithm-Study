class Solution {
    static int answer;
    static boolean isFound;
    static String[] alpha = {"A", "E", "I", "O", "U"};
    public int solution(String word) {
        per(0, "", word);
        return answer;
    }
    
    public static void per(int cnt, String str, String word) {
        if(str.equals(word)) {
            isFound = true;
            return;
        }
        
        if(cnt == 5) return;
        
        for(int i = 0; i < 5; i++) {
            if(isFound) return;
            answer++;
            per(cnt+1, str+alpha[i], word);
        }
    }
}