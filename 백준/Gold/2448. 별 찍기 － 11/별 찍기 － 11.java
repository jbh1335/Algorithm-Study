import java.io.*;

public class Main {
    static String[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new String[N];
        arr[0] = "  *  ";
        arr[1] = " * * ";
        arr[2] = "*****";

        for(int s = 6; s <= N; s*=2) {
            makeStar(s);
        }

        for(int i = 0; i < N; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void makeStar(int size) {
        int mid = size / 2;

        // 위의 별을 2개 붙인 아래쪽
        for(int i = mid; i < size; i++) {
            arr[i] = arr[i-mid] + " " + arr[i-mid];
        }

        StringBuilder sb = new StringBuilder();
        int count = mid;
        while(count-- > 0) {
            sb.append(" ");
        }
        // 위쪽 별
        for(int i = 0; i < mid; i++) {
            arr[i] = sb + arr[i] + sb;
        }
    }
}
