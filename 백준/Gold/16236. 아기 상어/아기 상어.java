import java.io.*;
import java.util.*;

/*
 * 1. 처음에 아기 상어의 크기는 2, 1초에 상하좌우로 인접한 한칸씩 이동
 * 		- 자기보다 크기가 큰 물고기가 있는 칸은 지나갈 수 없다. -> 같은 아이는 가능
 * 		- 자기보다 작은 물고기만 먹을 수 있다. -> 같은 아이는 못먹음
 * 이동 방법
 * 	1) 더이상 먹을 곳 없으면 엄마한테 도움 요청 -> 끝
 * 	2) 먹을 수 있는 물고기가 1마리면 먹으러감
 * 	3) 1마리보다 많으면 거리가 가장 가까운 물고기한테 감
 * 		1) 거리는 물고기가 있는 칸으로 이동할때 지나야하는 칸의 개수의 최솟값
 * 		2) 거리가 가까운 물고기가 많다면 가장 위에 있는 물고기부터 -> 행 작은거
 * 		3) 그것도 많으면 가장 왼쪽에 있는 물고기 -> 열 작은거
 * 2. 이동하는데 1초걸림, 물고기 먹으면 그 칸 빈칸
 * 3. 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1증가
 * 4. 몇초동안 엄마한테 도움 요청하지 않고 물고기 먹는지 구하기
 */
public class Main {
	static int N, sharkX, sharkY, fishEat, sharkSize = 2, time;
	static boolean noMoreFish;
	static int[][] graph;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static class Point implements Comparable<Point> {
		int x;
		int y;
		int dist;
		
		public Point(int x, int y, int dist) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
		}

		@Override
		public int compareTo(Point o) {
			// 가까운 거리
			if(this.dist == o.dist) {
				// 작은 행
				if(this.x == o.x) {
					// 작은 열
					return this.y - o.y;
				}
				return this.x - o.x;
			}
			return this.dist - o.dist;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		graph = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				// 상어 위치 저장
				if(graph[i][j] == 9) {
					sharkX = i;
					sharkY = j;
				}
			}
		}
		
		while(!noMoreFish) {
			findFish();
		}
		
		System.out.println(time);
	}
	
	public static void findFish() {
		PriorityQueue<Point> pque = new PriorityQueue<>();
		pque.add(new Point(sharkX, sharkY, 0));
		visited = new boolean[N][N];
		visited[sharkX][sharkY] = true;
		graph[sharkX][sharkY] = 0;
		
		while(!pque.isEmpty()) {
			Point p = pque.poll();
			
			// 자기보다 작은 물고기만 먹을 수 있다. -> 같은 아이는 못먹음
			if(graph[p.x][p.y] < sharkSize && graph[p.x][p.y] != 0) {
//				System.out.println("<물고기 먹기>");
				// 한마리 먹어주고
				fishEat++;
//				System.out.println("먹은 물고기양: " + fishEat);
//				System.out.println("이동거리: " + p.dist);
				// 상어 위치 변경
				sharkX = p.x;
				sharkY = p.y;
//				System.out.println("상어 위치: " + sharkX + " " + sharkY);
				// 먹었으니 물고기 없어짐
				graph[p.x][p.y] = 0;
				// 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1증가
				if(fishEat == sharkSize) {
					sharkSize++;
//					System.out.println(fishEat + "개 먹어서 상어 크기 증가: " + sharkSize);
					fishEat = 0;
				}
				// 걸린 시간
				time += p.dist;
//				System.out.println("누적 시간: " + time);
				return;
			}
			
			for(int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				if(nx >= 0 && ny >= 0 && nx < N && ny < N) {
					// 자기보다 크기가 큰 물고기가 있는 칸은 지나갈 수 없다. -> 같은 아이는 가능
					if(!visited[nx][ny] && sharkSize >= graph[nx][ny]) {
						pque.add(new Point(nx, ny, p.dist+1));
						visited[nx][ny] = true;
					}
				}
			}
		}
//		System.out.println("먹을게 없음");
		noMoreFish = true;
	}

}
