class Solution {
    public int solution(int[] a) {
        if(a.length == 1) return 1;
        int answer = 2;
        
        // 자기 왼쪽에 있는 애들 중 최솟값 구하기
        int[] leftMin = new int[a.length];
        leftMin[0] = a[0];
        for(int i = 1; i < a.length; i++) {
            leftMin[i] = Math.min(leftMin[i-1], a[i]);
        }
        
        // 자기 오른쪽에 있는 애들 중 최솟값 구하기
        int[] rightMin = new int[a.length];
        rightMin[a.length-1] = a[a.length-1];
        for(int i = a.length-2; i >= 0; i--) {
            rightMin[i] = Math.min(rightMin[i+1], a[i]);
        }
        
        for(int i = 1; i < a.length-1; i++) {
            if(a[i] > leftMin[i] && a[i] > rightMin[i]) continue;
            answer++;
        }
        return answer;
    }
}