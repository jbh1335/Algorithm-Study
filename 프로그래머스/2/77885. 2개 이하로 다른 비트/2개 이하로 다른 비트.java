class Solution {
    /*
        짝수: 마지막에 +1해주면됨
        홀수: 
            1) 1로만 이루어진 경우(2^n-1)
                ex) 1111 -> 10111
                1111에서 1을 더하면 10000 
                이미 맨앞자리(1)수가 서로 다름
                나머지 하나를 바꿀 수 있는데 가장 작은 수를 만들어야 하므로 가장 큰 자리수를 0으로 바꿈
                10111 -> 10 + 1*(원래 자리수-1)
            2) 0과 1로 이루어진 경우
                ex) 10111 -> 11011
                10111에서 1을 더하면 11000
                처음 0을 만날 때 1로 바꿈
                나머지 하나를 바꿀 수 있는데 마찬가지로 가장 큰 자리수를 0으로 바꿈
                11011 -> 원래 수 + 10 + 1*나머지
    */
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        
        for(int i = 0; i < numbers.length; i++) {
            // 짝수
            if(numbers[i] % 2 == 0) {
                answer[i] = numbers[i] + 1;
                continue;
            }
            
            String binaryNum = Long.toBinaryString(numbers[i]);
            String newBinaryNum = binaryNum.replace("0", "");
            StringBuilder sb = new StringBuilder();
            // 모두 1로 이루어진 홀수
            if(binaryNum.length() == newBinaryNum.length()) {
                sb.append("10");
                int count = binaryNum.length() - 1;
                
                while(count-- > 0) {
                    sb.append("1");
                }
            } else { // 0과 1이 섞인 홀수
                int idx = binaryNum.lastIndexOf("0");
                sb.append(binaryNum.substring(0, idx));
                sb.append("10");
                sb.append(binaryNum.substring(idx+2, binaryNum.length()));
            }
            
            answer[i] = Long.valueOf(sb.toString(), 2);
        }
        return answer;
    }
}