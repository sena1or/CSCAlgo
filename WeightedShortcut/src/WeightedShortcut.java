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

		int d = numberOfEdges / numberOfVertices;

		if (d < 2)
		{
			d = 2;
		}

		else
		{
			if (d > 10)
			{
				d = 10;
			}
		}

		BinaryHeap heap = new BinaryHeap(numberOfVertices, d);

		int start = nextInt();
		distance[start] = 0;
		heap.ChangeKey(distance[start], start);

		int currentVertex;

		while (!heap.IsEmpty())
		{
			currentVertex = heap.Extract();
			if (distance[currentVertex] == Integer.MAX_VALUE)
			{
				continue;
			}

			int next = vertices[currentVertex];

			while (next != 0)
			{
				if (distance[adjacency[0][next]] > distance[currentVertex] + adjacency[2][next])
				{
					distance[adjacency[0][next]] = distance[currentVertex] + adjacency[2][next];
					heap.ChangeKey(distance[adjacency[0][next]], adjacency[0][next]);
				}

				next = adjacency[1][next];
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

class BinaryHeap
{
	private int[] pointerTables;
	private int[][] heap;
	private int count;
	private int base;

	public BinaryHeap(int capacity, int base)
	{
		this.count = 0;
		this.base = base;
		this.heap = new int[2][HighestPowerOfBase(capacity)];
		this.pointerTables = new int[capacity + 1];

		Arrays.fill(this.heap[0], Integer.MAX_VALUE);

		while (count < capacity)
		{
			pointerTables[count + 1] = count;
			heap[1][count++] = count;
		}
	}

	public boolean IsEmpty()
	{
		return count == 0;
	}

	public void Insert(int key, int value)
	{
		this.heap[0][count] = key;
		this.heap[1][count] = value;
		SiftUp(count);
		count++;
	}

	private void SiftUp(int index)
	{
		int parent;
		while (index > 0)
		{
			parent = (index - 1) / base;
			if (this.heap[0][parent] > this.heap[0][index])
			{
				swap(index, parent);
				index = parent;
			}

			else
			{
				break;
			}
		}

	}

	private void swap(int index1, int index2)
	{
		int temp;
		temp = this.heap[0][index2];
		this.heap[0][index2] = this.heap[0][index1];
		this.heap[0][index1] = temp;

		int pointer = pointerTables[this.heap[1][index1]];
		pointerTables[this.heap[1][index1]] = pointerTables[this.heap[1][index2]];
		pointerTables[this.heap[1][index2]] = pointer;

		temp = this.heap[1][index2];
		this.heap[1][index2] = this.heap[1][index1];
		this.heap[1][index1] = temp;
	}

	public int Extract()
	{
		if (count == 0)
		{
			return -1;
		}

		int result = heap[1][0];
		count--;
		heap[0][0] = heap[0][count];
		heap[1][0] = heap[1][count];
		heap[0][count] = Integer.MAX_VALUE;
		SiftDown(0);
		return result;
	}

	private void SiftDown(int index)
	{
		while (index * base + 1 < this.count)
		{
			int minChildNumber = getMinChildNumber(index);

			if (heap[0][index] > heap[0][minChildNumber])
			{
				swap(index, minChildNumber);
				index = minChildNumber;
			}

			else
			{
				break;
			}
		}
	}

	private int getMinChildNumber(int index)
	{
		int childIndex = 2 * index + 1;
		int stop = 2 * index + base;
		int minChildIndex = childIndex++;

		while(childIndex < this.count && childIndex <= stop)
		{
			if (this.heap[0][minChildIndex] > this.heap[0][childIndex])
			{
				minChildIndex = childIndex;
			}

			childIndex++;
		}

		return minChildIndex;
	}

	private int HighestPowerOfBase(int v)
	{
		int power = 1;

		while (power < v)
		{
			power *= base;
		}
		return power;
	}

	public void ChangeKey(int key, int value)
	{
		this.heap[0][pointerTables[value]] = key;
		if (heap[0][(pointerTables[value] - 1) / 2] > heap[0][pointerTables[value]])
		{
			SiftUp(key);
		}
		else
		{
			SiftDown(key);
		}
	}
}