import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SegmentTree
{
	static StringTokenizer st;
	static BufferedReader in;
	static PrintWriter out;

	public static void main(String[] args)
	{
		try
		{
			in = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(new OutputStreamWriter(System.out));
			solve();
		} catch (Exception e)
		{
			e.printStackTrace();
			System.exit(9000);
		} finally
		{
			out.flush();
			out.close();
		}
	}

	private static String nextToken() throws IOException
	{
		while (st == null || !st.hasMoreTokens())
		{
			st = new StringTokenizer(in.readLine());
		}
		return st.nextToken();
	}

	private static int nextInt() throws NumberFormatException, IOException
	{
		return Integer.parseInt(nextToken());
	}

	private static long nextLong() throws NumberFormatException, IOException
	{
		return Long.parseLong(nextToken());
	}

	private static double nextDouble() throws NumberFormatException, IOException
	{
		return Double.parseDouble(nextToken());
	}

	private static int count;
	private static int inputArray[];

	private static void solve() throws NumberFormatException, IOException
	{
		count = nextInt();
		int operationsCount = nextInt();

		inputArray = new int[2 * HighestPowerOfTwo(count)];
		Arrays.fill(inputArray, Integer.MAX_VALUE);

		for (int i = inputArray.length / 2; i < count + inputArray.length / 2; i++)
		{
			inputArray[i] = nextInt();
		}

		int start = inputArray.length / 2;
		int parent = start / 2;
		while (parent > 0)
		{
			int powerOfTwo = HighestPowerOfTwo(start + 1);
			for (int i = start; i < powerOfTwo; i += 2)
			{
				inputArray[i / 2] = Math.min(inputArray[i], inputArray[i + 1]);
			}
			start = start / 2;
			parent = start / 2;
		}

		for (int i = 0; i < operationsCount; i++)
		{
			String command = nextToken();

			if (command.contentEquals("Min"))
			{
				int l = nextInt() - 1 + inputArray.length / 2;
				int r = nextInt() - 1 + inputArray.length / 2;

				out.println(GetMin(l, r, inputArray.length / 2, inputArray.length - 1, 1));
			}

			if (command.contentEquals("Set"))
			{
				int index = nextInt() - 1 + inputArray.length / 2;
				int value = nextInt();

				UpdateTree(index, value, inputArray);
			}
		}
	}

	private static void UpdateTree(int index, int value, int[] inputArray)
	{
		inputArray[index] = value;
		while (true)
		{
			if (index == 2 || index == 3)
			{
				inputArray[1] = Math.min(inputArray[2], inputArray[3]);
				break;
			}

			inputArray[index / 2] = Math.min(inputArray[index - index % 2], inputArray[index + (index + 1) % 2]);

			index /= 2;
		}
	}

	private static int GetMin(int left, int right, int boundA, int boundB, int min)
	{
		if (left == boundA && right == boundB)
		{
			return inputArray[min];
		}

		if (left == right)
		{
			return inputArray[left];
		}

		int middle = (boundB + boundA) / 2;

		if (left > middle)
		{
			return GetMin(left, right, middle + 1, boundB, 2 * min + 1);
		}

		if (right <= middle)
		{
			return GetMin(left, right, boundA, middle, 2 * min);
		}

		return Math.min(
				GetMin(left, middle, boundA, middle, 2 * min),
				GetMin(middle + 1, right, middle + 1, boundB, 2 * min + 1));
	}

	private static int HighestPowerOfTwo(int v)
	{
		v--;
		v |= v >> 1;
		v |= v >> 2;
		v |= v >> 4;
		v |= v >> 8;
		v |= v >> 16;
		v++;

		return v;
	}
}

