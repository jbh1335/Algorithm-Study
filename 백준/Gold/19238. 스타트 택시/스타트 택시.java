import java.io.*;
import java.util.*;

/*
 * 1. 택시 위치에서 상하좌우 탐색하여 승객 있는 곳의 최단거리 찾기 
 * 		-> 택시랑 승객이 같은 위치에 있으면 최단거리 0
 * 	1) 만약 여러명이면 그 중 행 번호가 가장 작은 승객
 * 	2) 그것도 여러명이면 그중 열 번호가 가장 작은 승객
 * 2. 승객 태우고 그 사람 집까지 가기
 * 3. 한 칸마다 연료가 -1 (승객 데리러가고 집까지 도착하는거 다 포함)
 * 4. 승객의 집까지 성공하면 그 승객을 태우고 이동하면서 소모한 연료 양의 2배 충전 (혼자 데리러가는것은 X)
 * 5. 이동하는 도중에 연료가 바닥나면 실패 -> 끝
 * 		-> 이동시킨 동시에 연료가 바닥나는건 상관없음
 * 6. 모든 승객 데려다줄 수 있는지 알아내고, 남은 연료의 양 구하기
 */

public class Main {
	static int N, M, fuel, startX, startY;
	static boolean fail;
	static int[][] graph;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static Point[] home;
	static class Point {
		int startX;
		int startY;
		int endX;
		int endY;
		
		public Point(int startX, int startY, int endX, int endY) {
			super();
			this.startX = startX;
			this.startY = startY;
			this.endX = endX;
			this.endY = endY;
		}
	}
	static class PersonLocation implements Comparable<PersonLocation> {
		int x;
		int y;
		int dist;
		
		public PersonLocation(int x, int y, int dist) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
		}

		@Override
		public int compareTo(PersonLocation o) {
			// 최단 거리로 정렬
			// 만약 여러명이면 그 중 행 번호가 가장 작은 승객
			if(this.dist == o.dist) {
				// 그것도 여러명이면 그중 열 번호가 가장 작은 승객
				if(this.x == o.x) {
					return this.y - o.y;
				}
				return this.x - o.x;
			}
			return this.dist - o.dist;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // N*N
		M = Integer.parseInt(st.nextToken()); // 승객 수
		fuel = Integer.parseInt(st.nextToken()); // 연료
		
		graph = new int[N][N];
		home = new Point[M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j =0 ; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 택시 시작 지점
		st = new StringTokenizer(br.readLine());
		startX = Integer.parseInt(st.nextToken()) - 1;
		startY = Integer.parseInt(st.nextToken()) - 1;
		
		int num = 2;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken()) - 1;
			int y1 = Integer.parseInt(st.nextToken()) - 1;
			int x2 = Integer.parseInt(st.nextToken()) - 1;
			int y2 = Integer.parseInt(st.nextToken()) - 1;
			
			graph[x1][y1] = num;
			home[i] = new Point(x1, y1, x2, y2);
			num++;
		}
		
//		System.out.println("========");
//		for(int i = 0; i < N; i++) {
//			for(int j =0 ; j < N; j++) {
//				System.out.print(graph[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println("========");
		
		for(int i = 0; i < M; i++) {
			// 시작지점에 사람있으면 탐색 X
//			System.out.println("--------------------");
//			System.out.println("첫 시작 -> startX: " + startX + " startY: " + startY + " 남은 연료: " + fuel);
			if(graph[startX][startY] == 0) findPerson(startX, startY);
			else graph[startX][startY] = 0;
//			System.out.println("사람 찾고 난 후 -> startX: " + startX + " startY: " + startY + " 남은 연료: " + fuel);
			
			if(fail) {
				fuel = -1;
				break;
			}
			
			goHome(startX, startY);
//			System.out.println("집 데려다주고 -> startX: " + startX + " startY: " + startY + " 남은 연료: " + fuel);
			
			if(fail) {
				fuel = -1;
				break;
			}
			
//			System.out.println("========");
//			for(int a = 0; a < N; a++) {
//				for(int j =0 ; j < N; j++) {
//					System.out.print(graph[a][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println("========");
		}
		System.out.println(fuel);
	}

	// 택시 위치에서 상하좌우 탐색하여 승객 있는 곳의 최단거리 찾기 
	public static void findPerson(int x, int y) { 
		PriorityQueue<PersonLocation> pque = new PriorityQueue<>();
		pque.add(new PersonLocation(x, y, 0));
		visited = new boolean[N][N];
		visited[x][y] = true;
		
		while(!pque.isEmpty()) {
			PersonLocation p = pque.poll();
			
			// 사람이 있으면
			if(graph[p.x][p.y] > 1) {
//				System.out.println("person 위치: " + nx + " " + ny);
				// 택시의 시작 지점으로 설정해주고
				startX = p.x;
				startY = p.y;
				
				if(fuel <= p.dist) {
					fail = true;
					return;
				}
				// 사용한 연료만큼 빼주기
				fuel -= p.dist;
//				System.out.println("사용한 연료: " + (p.dist+1));
				// 사람 태웠으니 빈칸으로 만들기
				graph[p.x][p.y] = 0;
				
				return;
			}
			
			for(int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				if(rangeOk(nx, ny)) {
					pque.add(new PersonLocation(nx, ny, p.dist+1));
					visited[nx][ny] = true;
				}
			}
		}
		
		fail = true;
		return;
	}
	
	// 승객 태우고 그 사람 집까지 가기
	public static void goHome(int x, int y) {
		Queue<PersonLocation> que = new LinkedList<>();
		que.add(new PersonLocation(x, y, 0));
		visited = new boolean[N][N];
		visited[x][y] = true;
		int homeX = -1, homeY = -1;
		
		// 승객의 집 위치를 찾기
		for(int i = 0; i < M; i++) {
			if(home[i].startX == x && home[i].startY == y) {
				homeX = home[i].endX;
				homeY = home[i].endY;
			}
		}
		
//		System.out.println("도착지점 X: " + homeX + " Y: " + homeY);
		
		while(!que.isEmpty()) {
			PersonLocation p = que.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				if(rangeOk(nx, ny)) {
					que.add(new PersonLocation(nx, ny, p.dist+1));
					visited[nx][ny] = true;
					
					// 승객의 집을 찾으면
					if(nx == homeX && ny == homeY) {
//						System.out.println("home 위치 " + nx + " " + ny);
						// 만약에 남은연료로 집까지 다 이동못하면 실패
						if(fuel < p.dist+1) {
							fail = true;
							return;
						}
						// 이동한 만큼 연료 빼주고 2배만큼 더해줘야하므로 
						// 결국 이동한 만큼만 더해주기 
						fuel += (p.dist + 1);
//						System.out.println("집데려다 주는데 사용 연료: " + (p.dist+1));
						// 택시의 시작 지점으로 설정
						startX = homeX;
						startY = homeY;
						return;
					}
				}
			}
		}
		
		fail = true;
		return;
	}
	
	// 범위 맞고 방문한 적 없고 벽이 아니면 성공
	public static boolean rangeOk(int nx, int ny) {
		if(nx >= 0 && ny >= 0 && nx < N && ny < N) {
			if(!visited[nx][ny] && graph[nx][ny] != 1) {
				return true;
			}
		}
		return false;
	}
}

/*
6 3 100
0 0 1 0 0 0
0 0 1 0 0 0
0 0 0 1 0 0
0 0 0 1 0 0
0 0 0 0 1 0
0 0 0 1 0 0
6 3
2 2 5 4
5 6 1 6
4 2 3 3
 */
