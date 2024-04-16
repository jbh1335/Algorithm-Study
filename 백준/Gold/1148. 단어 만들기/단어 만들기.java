import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 사전에 있는 단어
        ArrayList<String> dictionaryList = new ArrayList<>();
        while(true) {
            String str = br.readLine();
            if(str.equals("-")) break;
            dictionaryList.add(str);
        }

        // 퍼즐판
        ArrayList<String> puzzleList = new ArrayList<>();
        while(true) {
            String str = br.readLine();
            if(str.equals("#")) break;
            puzzleList.add(str);
        }

        // 사전에 있는 모든 단어의 상태 저장
        HashMap<String, HashMap<Character, Integer>> dictionaryMap = new HashMap<>();
        for(String str : dictionaryList) {
            // str이 어떤 알파벳으로 몇개씩 구성되어있는지 저장
            HashMap<Character, Integer> map = new HashMap<>();
            for(int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                map.put(ch, map.getOrDefault(ch, 0) + 1);
            }
            dictionaryMap.put(str, map);
        }

        // 퍼즐판에서 검사
        for(String puzzle : puzzleList) {
            // 퍼즐판의 알파벳 구성 상태 저장
            HashMap<Character, Integer> puzzleMap = new HashMap<>();
            // 퍼즐판에서 만들 수 있는 모든 단어의 알파벳 구성 상태 저장
            // -> 가장 많이 사용하거나 적게 사용하는 알파벳 찾기 위해 (퍼즐판 가운데)
            HashMap<Character, Integer> totalMap = new HashMap<>();
            for(int i = 0; i < puzzle.length(); i++) {
                char ch = puzzle.charAt(i);
                puzzleMap.put(ch, puzzleMap.getOrDefault(ch, 0) + 1);
                totalMap.put(ch, 0);
            }

            // 사전에 있는 단어 중 만들 수 있는 것들 찾기
            ArrayList<String> ableList = new ArrayList<>();
            for(String str : dictionaryList) {
                HashMap<Character, Integer> map = dictionaryMap.get(str);
                boolean flag = true;
                for(char ch : map.keySet()) {
                    // 만들 수 없다면 멈추기
                    if(!puzzleMap.containsKey(ch) || map.get(ch) > puzzleMap.get(ch)) {
                        flag = false;
                        break;
                    }
                }

                // 만들 수 있으면 저장
                if(flag) {
                    ableList.add(str);
                    for(char ch : map.keySet()) {
                        totalMap.put(ch, totalMap.get(ch) + 1);
                    }
                }
            }

            // 가장 많이 사용하는 알파벳과 적게 사용하는 알파벳 찾기
            ArrayList<Character> list = new ArrayList<>(totalMap.keySet());
            Collections.sort(list, (o1, o2) -> totalMap.get(o1) - totalMap.get(o2));

            int min = totalMap.get(list.get(0));
            String minStr = "";
            for(char ch : list) {
                if(totalMap.get(ch) > min) break;
                minStr += ch;
            }

            int max = totalMap.get(list.get(list.size()-1));
            String maxStr = "";
            for(int i = list.size()-1; i >= 0; i--) {
                char ch = list.get(i);
                if(totalMap.get(ch) < max) break;
                maxStr += ch;
            }

            // 오름차순 정렬
            char[] minArr = minStr.toCharArray();
            Arrays.sort(minArr);
            minStr = new String(minArr);

            char[] maxArr = maxStr.toCharArray();
            Arrays.sort(maxArr);
            maxStr = new String(maxArr);
            System.out.println(minStr + " " + min + " " + maxStr + " " + max);
        }
    }
}
