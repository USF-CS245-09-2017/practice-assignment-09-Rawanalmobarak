import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Stack;


public class GraphAdjMatrix implements Graph {
	
	int[][] edges;
	int size;


	public GraphAdjMatrix(int size) {
		this.size = size;
		edges = new int[size][size];
	}

	@Override
	public void addEdge(int v1, int v2) {
		edges[v1][v2] = 1;
		edges[v2][v1] = 1;

	}

	@Override
	public void topologicalSort() {
		boolean[] visited = new boolean[size];
		for (int i = 0; i < visited.length; i++) {
			if (!visited[i]) {
				topologicalSort(i, visited);
			}
		}

	}
	
	private void topologicalSort(int vertex, boolean[] visited) {
		Stack<Integer> s = new Stack<Integer>();
		s.push(new Integer(vertex));
		visited[vertex] = true;
		while (!s.empty()) {
			int u = s.pop();
			System.out.print(u + " ");
			Iterator<Integer> it = Arrays.stream(neighbors(u)).iterator();
			while (it.hasNext()) {
				int v = it.next();
				if (!visited[v]) {
					visited[v] = true;
					s.push(new Integer(v));
				}
			}
		}
		System.out.println();
	}
	
	public int[] neighbors(int vertex) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < edges.length; i++) {
			if (edges[vertex][i] == 1) {
				result.add(i);
			}
		}
		int[] array = result.stream().mapToInt(i -> i).toArray();
		return array;
	}

	@Override
	public void addEdge(int v1, int v2, int weight) {
		edges[v1][v2] = weight;

	}

	@Override
	public int getEdge(int v1, int v2) {
		return edges[v1][v2];
	}

	@Override
	public int createSpanningTree() {
		return size;
	}
	
	public Graph getGraph(int size) {
		return new GraphAdjMatrix(size);
	}
	



}
