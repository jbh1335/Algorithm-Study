class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        // 재생된 시간 최댓값
        int max = 0;
        m = changeMelody(m);
        
        for(int i = 0; i < musicinfos.length; i++) {
            String[] musicinfosArr = musicinfos[i].split(",");
            
            // 시작 시간을 분으로 변경
            String[] startArr = musicinfosArr[0].split(":");
            int startMin = Integer.parseInt(startArr[0]) * 60;
            startMin += Integer.parseInt(startArr[1]);
            
            // 끝 시간을 분으로 변경
            String[] endArr = musicinfosArr[1].split(":");
            int endMin = Integer.parseInt(endArr[0]) * 60;
            endMin += Integer.parseInt(endArr[1]);
            
            // 총 재생된 분
            int playMin = endMin - startMin;
            // 찾으려는 멜로디가 더 길면 불가능
            if(m.length() > playMin) continue;
            // 재생되는 음악
            String music = changeMelody(musicinfosArr[3]);
            // playMin동안 재생되는 음악
            StringBuilder sb = new StringBuilder();
            if(playMin <= music.length()) {
                sb.append(music.substring(0, playMin));
            } else {
                // 반복되는 횟수
                int replay = playMin / music.length();
                while(replay-- > 0) {
                    sb.append(music);
                }
                
                // 남은 부분
                int left = playMin % music.length();
                if(left > 0) sb.append(music.substring(0, left));
            }
            
            // 조건에 맞는지 확인
            if(sb.toString().contains(m)) {
                if(playMin > max) {
                    max = playMin;
                    answer = musicinfosArr[2];
                }
            }
        }

        return answer;
    }
    
    // 길이를 위해 #을 다른 음으로 변경
    public static String changeMelody(String str) {
        str = str.replace("A#", "a").replace("C#", "c").replace("D#", "d")
                .replace("F#", "f").replace("G#", "g").replace("B#", "b");
        return str;
    }
}