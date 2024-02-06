import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            int k = Integer.parseInt(br.readLine());
            PriorityQueue<Integer> pqueUp = new PriorityQueue<>();
            PriorityQueue<Integer> pqueDown = new PriorityQueue<>(Collections.reverseOrder());
            HashMap<Integer, Integer> map = new HashMap<>();

            for(int i = 0; i < k; i++) {
                String str = br.readLine();
                String[] splitArr = str.split(" ");
                int num = Integer.parseInt(splitArr[1]);

                if(splitArr[0].equals("I")) {
                    pqueUp.offer(num);
                    pqueDown.offer(num);
                    map.put(num, map.getOrDefault(num, 0)+1);
                } else {
                    if(map.isEmpty()) continue;

                    if(num == -1) {
                        delete(pqueUp, map);
                    } else {
                        delete(pqueDown, map);
                    }
                }
            }

            if(map.isEmpty()) System.out.println("EMPTY");
            else {
                int max = delete(pqueDown, map);
                int min = map.isEmpty() ? max : delete(pqueUp, map);
                System.out.println(max + " " + min);
            }
        }
    }

    public static int delete(PriorityQueue<Integer> pque, HashMap<Integer, Integer> map) {
        int num = 0;
        while(true) {
            num = pque.poll();

            if(map.containsKey(num)) {
                if(map.get(num) == 1) map.remove(num);
                else map.replace(num, map.get(num)-1);
                break;
            }
        }
        return num;
    }
}
