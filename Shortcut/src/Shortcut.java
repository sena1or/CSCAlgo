import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Shortcut
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
		int[][] adjacency = new int[2][numberOfEdges * 2 + 1];

		for (int i = 1; i <= numberOfEdges * 2; i += 2)
		{
			int from, to;

			from = nextInt();
			to = nextInt();

			addEdge(vertices, adjacency, from, to, i);
			addEdge(vertices, adjacency, to, from, i + 1);
		}

		int[] distance = new int[numberOfVertices + 1];

		Arrays.fill(distance, -1);

		bfs(vertices, adjacency, nextInt(), distance);

		out.print(distance[nextInt()]);
	}

	private static void bfs(int[] vertices, int[][] adjacency, int start, int[] distance)
	{
		int[] bfsQueue = new int[vertices.length];
		int begin = 0, end = 0;

		bfsQueue[end++] = start;
		distance[start] = 0;

		while (begin != end)
		{
			int currentVertex = bfsQueue[begin++];
			int next = vertices[currentVertex];

			while (next != 0)
			{
				if (distance[adjacency[0][next]] == -1)
				{
					distance[adjacency[0][next]] = distance[currentVertex] + 1;

					bfsQueue[end++] = adjacency[0][next];
				}

				next = adjacency[1][next];
			}
		}
	}

	private static void addEdge(int[] vertices, int[][] adjacency, int from, int to, int edgeNumber)
	{
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
	}
}