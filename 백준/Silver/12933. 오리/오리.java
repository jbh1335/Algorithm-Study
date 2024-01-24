import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String noise = br.readLine();
        ArrayList<Character> duckList = new ArrayList<>();
        duckList.add(noise.charAt(0));
        String duck = "quack";

        boolean goodNoise = true;
        for(int i = 1; i < noise.length(); i++) {
            char ch = noise.charAt(i); // 현재 소리
            int idx = duck.indexOf(ch); // duck에서 현재 소리의 인덱스 값

            // 오리 중에 이어서 소리를 내는 것인지 확인
            boolean sameDuck = false;
            for(int j = 0; j < duckList.size(); j++) {
                char lastCh = duckList.get(j);
                if(idx == 0) idx = 5;

                // 원래 존재하는 오리에서 이어지는 소리라면
                if(lastCh == duck.charAt(idx-1)) {
                    duckList.set(j, ch); // 현재 소리로 변경
                    sameDuck = true;
                    break;
                }
            }

            // 새로운 오리 등장
            if(!sameDuck) {
                // q로 시작하지 않으면 녹음 소리가 올바르지 않음
                if(ch == 'q') duckList.add(ch);
                else {
                    goodNoise = false;
                    break;
                }
            }
        }

        // 모든 오리들의 소리가 잘 마무리 되었는지 확인
        for(char c : duckList) {
            if(c != 'k') {
                goodNoise = false;
                break;
            }
        }

        if(goodNoise) System.out.println(duckList.size());
        else System.out.println("-1");
    }
}
