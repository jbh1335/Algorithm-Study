class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        for(int i = 0; i < skill_trees.length; i++) {
            String str = skill_trees[i].replaceAll("[^" + skill + "]", "");
            
            if(str.equals(skill)) answer++;
            else {
                boolean flag = true;
                for(int j = 0; j < str.length(); j++) {
                    if(skill.charAt(j) != str.charAt(j)) {
                        flag = false;
                        break;
                    }
                }
                if(flag) answer++;
            }
        }
        return answer;
    }
}