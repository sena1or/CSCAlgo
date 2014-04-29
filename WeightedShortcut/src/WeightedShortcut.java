import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


public class WeightedShortcut
{
	static StringTokenizer st;
	static BufferedReader in;
	static PrintWriter out;

	public static void main(String[] args) {
		try {
			in = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(new OutputStreamWriter(System.out));
			solve();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(9000);
		} finally {
			out.flush();
			out.close();
		}
	}

	private static String nextToken() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			st = new StringTokenizer(in.readLine());
		}
		return st.nextToken();
	}

	private static int nextInt() throws NumberFormatException, IOException {
		return Integer.parseInt(nextToken());
	}

	private static long nextLong() throws NumberFormatException, IOException {
		return Long.parseLong(nextToken());
	}

	private static double nextDouble() throws NumberFormatException, IOException {
		return Double.parseDouble(nextToken());
	}

	private static void solve() throws NumberFormatException, IOException
	{
		int numberOfVertices = nextInt();
		int numberOfEdges = nextInt();

		int[] vertices = new int[numberOfVertices + 1];
		int[][] adjacency = new int[3][numberOfEdges * 2 + 1];

		for (int i = 1; i <= numberOfEdges * 2; i += 2)
		{
			addEdge(vertices, adjacency, i);
		}

		int[] distance = new int[numberOfVertices + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);

		int start = nextInt();
		int obtainedVertices = 0;
		int currentVertex = start;
		boolean[] obtained = new boolean[numberOfVertices + 1];

		distance[start] = 0;
		obtained[start] = true;
		obtainedVertices++;

		while (obtainedVertices <= numberOfVertices)
		{
			if (distance[currentVertex] == Integer.MAX_VALUE)
			{
				break;
			}

			int next = vertices[currentVertex];

			while (next != 0)
			{
				if (distance[adjacency[0][next]] > distance[currentVertex] + adjacency[2][next])
				{
					distance[adjacency[0][next]] = distance[currentVertex] + adjacency[2][next];
				}

				next = adjacency[1][next];
			}

			obtained[currentVertex] = true;
			obtainedVertices++;
			currentVertex = 0;

			for (int i = 1; i <= numberOfVertices; i++)
			{
				if (distance[currentVertex] > distance[i] && !obtained[i])
				{
					currentVertex = i;
				}
			}
		}

		int stop = nextInt();
		out.println(distance[stop] == Integer.MAX_VALUE ? -1 : distance[stop]);
	}

	private static void addEdge(int[] vertices, int[][] adjacency, int edgeNumber)
			throws IOException
	{
		int from, to, weight;

		from = nextInt();
		to = nextInt();
		weight = nextInt();

		if (vertices[from] == 0)
		{
			vertices[from] = edgeNumber;
		}

		else
		{
			int next = vertices[from];

			while (adjacency[1][next] != 0)
			{
				next = adjacency[1][next];
			}

			adjacency[1][next] = edgeNumber;
		}

		adjacency[0][edgeNumber] = to;
		adjacency[2][edgeNumber] = weight;
	}
}