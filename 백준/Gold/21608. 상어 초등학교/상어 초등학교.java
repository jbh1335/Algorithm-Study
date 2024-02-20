import java.io.*;
import java.util.*;

/*
    N*N 교실이 있고 학생 수는 N^2명이다.
    각 학생이 좋아하는 학생 4명이 주어지면 다음 규칙에 따라 자리를 정하려고 한다.
    1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸이 많은 곳으로 정한다.
    2. 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 카으로 정한다.
    3. 2를 만족하는 칸도 여러 개이면, 행의 번호가 가장 작은 칸으로, 그것도 여러 개이면 열의 번호가 작은 칸으로 정한다.
    학생의 자리를 다 정했으면 학생의 만족도를 구해야 한다.
        -> 학생과 인접한 칸에 자신이 좋아하는 학생의 수를 구해야함 (0명: 0점, 1명: 1점, 2명: 10점, 3명: 100점, 4명: 1000점)
 */
public class Main {
    static int N;
    static int[][] seat, likeStudent;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static ArrayList<Integer> studentOrder;
    static ArrayList<Point> seatList;
    static class Point {
        int x, y, nearLikeStudentCnt, nearEmptySeatCnt;
        public Point(int x, int y, int nearLikeStudentCnt, int nearEmptySeatCnt) {
            this.x = x;
            this.y = y;
            this.nearLikeStudentCnt = nearLikeStudentCnt;
            this.nearEmptySeatCnt = nearEmptySeatCnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        seat = new int[N][N]; // 자리
        likeStudent = new int[N*N+1][4]; // 학생이 좋아하는 학생 4명 저장
        studentOrder = new ArrayList<>(); // 자리를 정할 순서대로 학생 번호 저장

        for(int i = 1; i <= N*N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());
            studentOrder.add(student);

            for(int j = 0; j < 4; j++) {
                likeStudent[student][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 학생 자리 저장
        for(int student : studentOrder) {
            emptySeat(student);
            // 조건에 맞게 정렬
            Collections.sort(seatList, (p1, p2) -> {
                if(p1.nearLikeStudentCnt == p2.nearLikeStudentCnt) {
                    if(p1.nearEmptySeatCnt == p2.nearEmptySeatCnt) {
                        if(p1.x == p2.x) return p1.y - p2.y;
                        return p1.x - p2.x;
                    }
                    return p2.nearEmptySeatCnt - p1.nearEmptySeatCnt;
                }
                return p2.nearLikeStudentCnt - p1.nearLikeStudentCnt;
            });

            // 자리 지정
            Point p = seatList.get(0);
            seat[p.x][p.y] = student;
        }

        // 학생의 만족도 검사
        int answer = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                int likeCnt = nearLikeStudent(i, j, seat[i][j])[0];
                if(likeCnt == 2) likeCnt = 10;
                else if(likeCnt == 3) likeCnt = 100;
                else if(likeCnt == 4) likeCnt = 1000;

                answer += likeCnt;
            }
        }
        System.out.println(answer);
    }

    // 비어있는 칸 구하기
    public static void emptySeat(int student) {
        seatList = new ArrayList<>(); // 해당 조건에 만족하는 자리를 저장
        int likeCnt = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(seat[i][j] == 0) {
                    int[] arr = nearLikeStudent(i, j, student);
                    if(arr[0] >= likeCnt) {
                        likeCnt = arr[0];
                        seatList.add(new Point(i, j, arr[0], arr[1]));
                    }
                }
            }
        }
    }

    // 인접한 칸에 좋아하는 학생이 몇명 있는지와 비어있는 자리 수 구하기
    public static int[] nearLikeStudent(int x, int y, int student) {
        // arr[0]은 좋아하는 학생 수, arr[1]은 비어있는 자리 수
        int[] arr = new int[2];
        for(int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(checkRange(nx, ny)) {
                for(int i = 0; i < 4; i++) {
                    if(seat[nx][ny] == likeStudent[student][i]) arr[0]++;
                    else if(seat[nx][ny] == 0) arr[1]++;
                }
            }
        }
        return arr;
    }

    public static boolean checkRange(int nx, int ny) {
        if(nx >= 0 && ny >= 0 && nx < N && ny < N) return true;
        return false;
    }
}
