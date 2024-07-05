class Solution {
    public int solution(int order) {
        String str = String.valueOf(order).replaceAll("[^369]", "");
        return str.length();
    }
}