import java.io.*;
import java.util.Random;
import java.util.StringTokenizer;


public class CheckCycles
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

		int[][] vertices = new int[3][numberOfVertices + 1];
		int[][] adjacency = new int[2][numberOfEdges * 2 + 1];

		for (int i = 1; i <= numberOfEdges; i++)
		{
			int from, to;

			from = nextInt();
			to = nextInt();

			addEdge(vertices, adjacency, from, to, i);
		}

		boolean[] visited = new boolean[numberOfVertices + 1];

		for (int i = 1; i <= numberOfVertices; i++)
		{
			if (!visited[i])
			{
				if (dfs(vertices, adjacency, i, visited))
				{
					out.print("True");
					return;
				}
			}
		}

		out.print("False");
	}

	private static int time = 0;

	private static boolean dfs(int[][] vertices, int[][] adjacency, int from, boolean[] visited)
	{
		if (visited[from])
		{
			if (vertices[2][from] == 0)
			{
				return true;
			}

			return false;
		}

		int next = vertices[0][from];
		visited[from] = true;
		vertices[1][from] = ++time;

		while (next != 0)
		{

			if (dfs(vertices, adjacency, adjacency[0][next], visited))
			{
				return true;
			}

			next = adjacency[1][next];
		}

		vertices[2][from] = ++time;
		return false;
	}

	private static void addEdge(int[][] vertices, int[][] adjacency, int from, int to, int edgeNumber)
	{
		if (vertices[0][from] == 0)
		{
			vertices[0][from] = edgeNumber;
		}

		else
		{
			int next = vertices[0][from];

			while (adjacency[1][next] != 0)
			{
				next = adjacency[1][next];
			}

			adjacency[1][next] = edgeNumber;
		}

		adjacency[0][edgeNumber] = to;
	}
}