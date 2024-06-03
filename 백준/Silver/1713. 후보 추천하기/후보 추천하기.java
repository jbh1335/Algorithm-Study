import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        ArrayList<Integer> list = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            // 사진
            int picture = Integer.parseInt(st.nextToken());

            if(list.size() < N) { // 비어있는 사진 있으면 추가
                // 게시된 사진이 아니면 새로 추가
                if(!map.containsKey(picture)) list.add(picture);
            } else { // 비어있는 사진이 없으면
                if(!map.containsKey(picture)) {
                    // 가장 적게 추천받은 횟수
                    int min = Integer.MAX_VALUE;
                    for(int num : map.keySet()) {
                        min = Math.min(min, map.get(num));
                    }

                    // 2명 이상일 경우 가장 오래된 사진을 삭제
                    for(int j = 0; j < list.size(); j++) {
                        if(map.get(list.get(j)) == min) {
                            // 추천받은 횟수가 가장 적은 사진을 삭제하고
                            map.remove(list.get(j));
                            list.remove(j);
                            // 그 자리에 새롭게 추가
                            list.add(picture);
                            break;
                        }
                    }
                }
            }

            // 사진 횟수 증가
            map.put(picture, map.getOrDefault(picture, 0)+1);
        }

        Collections.sort(list);
        for(int i : list) {
            System.out.print(i + " ");
        }
    }
}
