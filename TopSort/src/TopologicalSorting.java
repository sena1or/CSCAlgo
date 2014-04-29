import java.io.*;
import java.util.Queue;
import java.util.StringTokenizer;


public class TopologicalSorting
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

		int[][] vertices = new int[2][numberOfVertices + 1];
		int[][] adjacency = new int[2][numberOfEdges * 2 + 1];

		for (int i = 1; i <= numberOfEdges; i++)
		{
			int from, to;

			from = nextInt();
			to = nextInt();

			addEdge(vertices, adjacency, from, to, i);
		}

		int[] result = new int[numberOfVertices];
		boolean[] obtained = new boolean[numberOfVertices + 1];


		int[] queueOfSource = new int[numberOfVertices + 1];
		int begin = 0, end = 0;

		for (int i = 1; i <= numberOfVertices; i++)
		{
			if (vertices[1][i] == 0 && !obtained[i])
			{
				queueOfSource[end++] = i;

				while (begin != end)
				{
					int currentSource = queueOfSource[begin++];

					obtained[currentSource] = true;

					int next = vertices[0][currentSource];

					while (next > 0)
					{
						if (--vertices[1][adjacency[0][next]] == 0 && !obtained[adjacency[0][next]])
						{
							queueOfSource[end++] = adjacency[0][next];
						}

						next = adjacency[1][next];
					}

					out.print(currentSource + " ");
				}
			}
		}
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
		vertices[1][to]++;
	}
}