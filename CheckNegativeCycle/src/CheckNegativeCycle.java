import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


public class CheckNegativeCycle
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

		int[][] edges = new int[3][numberOfEdges];

		for (int i = 0; i < numberOfEdges; i++)
		{
			edges[0][i] = nextInt();
			edges[1][i] = nextInt();
			edges[2][i] = nextInt();
		}

		int[] distance = new int[numberOfVertices + 1];

		distance[numberOfVertices] = 0;

		for (int i = 1; i <= numberOfVertices; i++)
		{
			for (int j = 0; j < numberOfEdges; j++)
			{
				if (distance[edges[1][j]] > distance[edges[0][j]] + edges[2][j])
				{
					distance[edges[1][j]] = Math.max(distance[edges[0][j]] + edges[2][j], Integer.MIN_VALUE);
				}
			}
		}

		for (int j = 0; j < numberOfEdges; j++)
		{
			if (distance[edges[1][j]] > distance[edges[0][j]] + edges[2][j])
			{
				out.println("True");
				return;
			}
		}

		out.println("False");
	}
}