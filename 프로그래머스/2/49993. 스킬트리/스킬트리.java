class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        for(String str : skill_trees) {
            str = str.replaceAll("[^" + skill + "]", "");
            if(skill.startsWith(str)) answer++;
        }
        
        return answer;
    }
}