class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 0;
        int game = gameNum(n);
        if(a > b) {
            int tmp = b;
            b = a;
            a = tmp;
        }
        
        while(true) {
            n /= 2;
            if(a <= n && n < b) {
                answer = game;
                break;
            } else {
                if(n < a) {
                    a -= n;
                    b -= n;
                }
                game--;
            }
        }
        
        return answer;
    }
    
    public static int gameNum(int num) {
        int k = 0;
        while(true) {
            int pow = (int) Math.pow(2, ++k);
            if(pow == num) break;
        }
        return k;
    }
}