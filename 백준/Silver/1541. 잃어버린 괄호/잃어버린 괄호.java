import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String equation = br.readLine();

        String[] splitNum = equation.split("[-+]");
        String[] splitOp = equation.split("[0-9]+");

        int answer = Integer.parseInt(splitNum[0]);
        for(int i = 1; i < splitNum.length; i++) {
            int num = Integer.parseInt(splitNum[i]);
            if(splitOp[i].equals("-")) {
                for(int j = i+1; j < splitOp.length; j++) {
                    if(splitOp[j].equals("+")) {
                        num += Integer.parseInt(splitNum[j]);
                        i = j;
                    } else {
                        break;
                    }
                }
                answer -= num;
            } else {
                answer += num;
            }
        }
        System.out.println(answer);
    }
}
