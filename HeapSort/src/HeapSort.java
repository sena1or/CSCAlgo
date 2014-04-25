import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


public class HeapSort{
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

	private static void solve() throws NumberFormatException, IOException {
		int count = nextInt();
		int array[] = new int[count];

		for (int i = 0; i < count; i++)
		{
			array[i] = nextInt();
		}

		BinaryHeap heap = new BinaryHeap(array);
		array = heap.Sort();

		for (int i = 0; i < array.length; i++)
		{
			out.print(array[i] + " ");
		}
	}
}

class BinaryHeap {
	private int[] heap;
	private int count;

	public BinaryHeap(int[] array)
	{
		this.heap = array;
		this.count = array.length;

		for (int i = count / 2 - 1; i >= 0; i--)
		{
			this.SiftDown(i);
		}
	}

	public void SiftUp(int index) {
		int parent;
		while (index > 0)
		{
			parent = (index - 1) / 2;
			if (this.heap[parent] < this.heap[index])
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
		temp = this.heap[index2];
		this.heap[index2] = this.heap[index1];
		this.heap[index1] = temp;
	}

	public void SiftDown(int index)
	{
		while (index * 2 + 1 < this.count)
		{
			int maxChildNumber;

			if (2 * index + 2 < this.count && heap[2 * index + 1] < heap[2 * index + 2])
			{
				maxChildNumber = 2 * index + 2;
			}

			else
			{
				maxChildNumber = 2 * index + 1;
			}

			if (heap[index] < heap[maxChildNumber])
			{
				swap(index, maxChildNumber);
				index = maxChildNumber;
			}

			else
			{
				break;
			}
		}
	}

	public int[] Sort()
	{
		while (count > 0)
		{
			swap(0, count - 1);
			count--;
			SiftDown(0);
		}
		return this.heap;
	}
}