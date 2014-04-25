import java.io.*;
import java.util.StringTokenizer;


public class InversionCount
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

    private static void solve() throws NumberFormatException, IOException {
		int count = nextInt();
		int array[] = new int[count];

		for (int i = 0; i < count; i++)
		{
			array[i] = nextInt();
		}

		long inversions = inversionCount(array);
		out.println(inversions);
    }

	public static long inversionCount(int[] array) {
		long result = 0;
		if (array.length > 16) {

			int[] left = leftHalf(array);
			int[] right = rightHalf(array);

			result += inversionCount(left);
			result += inversionCount(right);

			result += merge(array, left, right);
		}
		else
		{
			for(int i = 0; i < array.length; i++)
			{
				for (int j = i + 1; j < array.length; j++)
				{
					if(array[i] > array[j])
					{
						result++;
					}
				}
			}

			InsertionSort(array);
		}

		return result;
	}

	private static void InsertionSort(int[] array) {
		for(int i = 1; i < array.length; i++)
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


	public static int[] leftHalf(int[] array) {
		int size1 = array.length / 2;
		int[] left = new int[size1];
		for (int i = 0; i < size1; i++) {
			left[i] = array[i];
		}
		return left;
	}

	public static int[] rightHalf(int[] array) {
		int size1 = array.length / 2;
		int size2 = array.length - size1;
		int[] right = new int[size2];
		for (int i = 0; i < size2; i++) {
			right[i] = array[i + size1];
		}
		return right;
	}

	public static long merge(int[] result, int[] left, int[] right)
	{
		int i, j, k;
		long inv_count = 0;

		i = 0;
		j = 0;
		k = 0;

		while ((i <= left.length - 1) && (j <= right.length - 1))
		{
			if (left[i] <= right[j])
			{
				result[k++] = left[i++];
			}
			else
			{
				result[k++] = right[j++];
				inv_count = inv_count + (left.length - i);
			}
		}

  		while (i <= left.length - 1)
		{
			result[k++] = left[i++];
		}

  		while (j <= right.length - 1)
		{
			result[k++] = right[j++];
		}

		return inv_count;
	}
}