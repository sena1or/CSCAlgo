import java.io.*;
import java.util.Random;
import java.util.StringTokenizer;


public class Counter
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
		int segmentNumber = nextInt();
		int dotsNumber = nextInt();

		int start[] = new int[segmentNumber];
		int stop[] = new int[segmentNumber];

		long upLine[] = new long[200001];
		long downLine[] = new long[200001];

		for (int i = 0; i < segmentNumber; i++)
		{
			start[i] = nextInt() + 100000;
			stop[i] = nextInt() + 100000;
		}

		quickSort(start, 0 , segmentNumber - 1);
		quickSort(stop, 0, segmentNumber - 1);

		int cnt = 0, index = 0;
		upLine[start[0]] = cnt;

		for (int i = start[0]; i <= stop[segmentNumber - 1]; i++)
		{
			upLine[i] = cnt;

			while (index < segmentNumber && i == start[index])
			{
				cnt++;
				index++;
			}

			if (cnt > upLine[i])
			{
				upLine[i] = cnt;
			}
		}

		cnt = 0;
		index = 0;
		for (int i = stop[0]; i <= stop[segmentNumber - 1]; i++)
		{
			downLine[i] = cnt;

			while (index < segmentNumber && i == stop[index])
			{
				cnt--;
				index++;
			}
		}

		for (int i = start[0]; i <= stop[segmentNumber - 1]; i++)
		{
			upLine[i] = upLine[i] + downLine[i];
		}

		for (int i = 0; i < dotsNumber; i++)
		{
			int dot = nextInt();
			out.print(upLine[dot + 100000] + " ");
		}
    }

	private static void quickSort(int[] array, int left, int right)
	{
		if (right - left < 9)
		{
			InsertionSort(array, left, right);
		}

		else
		{
			int parts[];
			while (left < right)
			{
				parts = partition(array, left, right);
				quickSort(array, left, parts[0] - 1);
				left = parts[1];
			}
		}
	}

	private static Random rndSrc = new Random();
	private static int[] partition(int[] array, int left, int right)
	{
		int separator = left + rndSrc.nextInt(right) % (right - left);
		swap(array, left, separator);

		int lbound = left;

		for (int i = left + 1; i <= right; i++)
		{
			if (array[i] < array[left])
			{
				lbound++;
				swap(array, lbound, i);
			}
		}

		swap(array, left, lbound);

		int rbound = lbound + 1;

		for (int i = lbound + 1; i <= right; i++)
		{
			if (array[i] == array[lbound])
			{
				swap(array, rbound, i);
				rbound++;
			}
		}

		return new int[] { lbound, rbound };
	}

	private static void swap(int[] array, int index1, int index2)
	{
		int temp;
		temp = array[index2];
		array[index2] = array[index1];
		array[index1] = temp;
	}

	private static void InsertionSort(int[] array, int left, int right) {
		for(int i = left; i <= right; i++)
		{
			int currElem = array[i];
			int prevKey = i - 1;

			while(prevKey >= 0 && array[prevKey] > currElem)
			{
				array[prevKey + 1] = array[prevKey];
				prevKey--;
			}

			array[prevKey + 1] = currElem;
		}
	}
}