class Solution {
    public String solution(String new_id) {
        // 1단계
        new_id = new_id.toLowerCase();
        // 2단계
        new_id = new_id.replaceAll("[^a-z0-9-_.]","");
        // 3단계
        while(true) {
            if(new_id.indexOf("..") != -1) new_id = new_id.replace("..", ".");
            else break;
        }
        // 4단계
        if(new_id.equals(".")) new_id = "";
        else {
            if(new_id.charAt(0) == '.') new_id = new_id.substring(1, new_id.length());
            if(new_id.charAt(new_id.length()-1) == '.') new_id = new_id.substring(0, new_id.length()-1);
        }
        // 5단계
        if(new_id.equals("")) new_id = "a";
        // 6단계
        if(new_id.length() >= 16) {
            new_id = new_id.substring(0, 15);
            if(new_id.charAt(14) == '.') new_id = new_id.substring(0, 14);
        }
        // 7단계
        if(new_id.length() <= 2) {
            char lastCh = new_id.charAt(new_id.length()-1);
            int howMany = 3 - new_id.length();
            for(int i = 0; i < howMany; i++) {
                new_id += lastCh;
            }
        }
        
        return new_id;
    }
}