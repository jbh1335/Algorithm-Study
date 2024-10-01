class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        for(String str : skill_trees) {
            String newStr = str.replaceAll("[^" + skill + "]", "");
            if(newStr.equals(skill.substring(0, newStr.length()))) answer++;
        }
        
        return answer;
    }
}