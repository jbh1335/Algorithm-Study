class Solution
{
    public int solution(String s)
    {
        int answer = 1;

        for(int i = s.length(); i >= 2; i--) {
            if(palindrome(i, s)) {
                answer = i;
                break;
            }
        }

        return answer;
    }
    
    public static boolean palindrome(int size, String s) {
        for(int i = 0; i <= s.length()-size; i++) {
            int end = i + size;
            int count = 1;
            boolean isSame = true;
            
            for(int j = i; j < i+size/2; j++) {
                if(s.charAt(j) != s.charAt(end-count++)) {
                    isSame = false;
                    break;
                }
            }
            if(isSame) return true;
        }
        return false;
    }
}