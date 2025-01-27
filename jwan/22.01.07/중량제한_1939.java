package DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class �߷�����_1939 {
	private static int n, m;
	private static ArrayList<ArrayList<Island>> list = new ArrayList<>();
	private static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int left = 0;
		int right = 0;
		for (int i = 0; i <= n; i++) {
			list.add(new ArrayList<>());
		}
		int max = 0;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(reader.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list.get(a).add(new Island(b, c));
			list.get(b).add(new Island(a, c));
			max = Math.max(max, c);
		}
		right = max;
		st = new StringTokenizer(reader.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		while (left <= right) {
			int mid = (left + right) / 2;
			visited = new boolean[n + 1];
			if (bfs(start, end, mid)) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		System.out.println(right);
	}

	private static boolean bfs(int start, int end, int mid) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		visited[start] = true;
		while (!q.isEmpty()) {

			int island = q.poll();

			if (island == end) {
				return true;
			}
			for (Island i : list.get(island)) {

				if (!visited[i.destination] && mid <= i.cost) {
					visited[i.destination] = true;
					q.add(i.destination);
				}
			}
		}
		return false;
	}

	static class Island {
		int destination, cost;
		Island(int destination, int cost) {
			this.destination = destination;
			this.cost = cost;
		}
	}
}
