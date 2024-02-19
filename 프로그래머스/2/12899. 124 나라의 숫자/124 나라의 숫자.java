class Solution {
    public String solution(int n) {
        StringBuilder sb = new StringBuilder();
        String[] arr = {"4", "1", "2"};
        
        while(n > 0) {
            // 3진법을 생각하여 나머지에 해당되는 인덱스 값 저장
            sb.append(arr[n%3]);
            // 3의 배수는 한번 더 돌기 때문에 -1 해줘야함
            if(n % 3 == 0) n--;
            n /= 3;
        }
        
        sb.reverse();
        return sb.toString();
    }
}