class Solution
{
    public int solution(String s)
    {
        int answer = 0;
        
        loop:
        for(int l = s.length(); l >= 1; l--) {
            for(int i = 0; i <= s.length()-l; i++) {
                boolean isAble = true;
                for(int j = 0; j < l/2; j++) {
                    if(s.charAt(i+j) != s.charAt(i+l-1-j)) {
                        isAble = false;
                        break;
                    }
                }
                
                if(isAble) {
                    answer = l;
                    break loop;
                }
            }
        }

        return answer;
    }
}