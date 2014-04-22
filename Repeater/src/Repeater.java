import java.io.*;
import java.util.StringTokenizer;


public class Repeater{
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

    private static void solve() throws NumberFormatException, IOException {
        int count = nextInt();
        int inputArray[] = new int[count];

        for (int i = 0; i < count; i++)
        {
            inputArray[i] = nextInt();
        }

        mergeSort(inputArray);

        out.print(CheckFrequency(inputArray));
    }

    private static String CheckFrequency(int[] inputArray) {
        int counter = 1;
        for (int i = 1; i < inputArray.length; i++)
        {
            if (inputArray[i - 1] == inputArray[i])
            {
                counter++;
                if (counter > inputArray.length / 2)
                {
                    return "True";
                }

            }
            else
            {
                counter = 1;
                if (i > inputArray.length / 2)
                {
                    return "False";
                }
            }
        }
        return "False";
    }

    public static void mergeSort(int[] array) {
        if (array.length > 8) {
            // split array into two halves
            int[] left = leftHalf(array);
            int[] right = rightHalf(array);

            // recursively sort the two halves
            mergeSort(left);
            mergeSort(right);

            // merge the sorted halves into a sorted whole
            merge(array, left, right);
        }
        else
        {
            selectionSort(array);
        }
    }

    private static void selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++)
        {
            int next = i;
            for (int j = i + 1; j < array.length; j++)
            {
                if (array[next] > array[j])
                {
                    next = j;
                }
            }

            if (next != i)
            {
                int temp = array[i];
                array[i] = array[next];
                array[next] = temp;
            }
        }
    }

    // Returns the first half of the given array.
    public static int[] leftHalf(int[] array) {
        int size1 = array.length / 2;
        int[] left = new int[size1];
        for (int i = 0; i < size1; i++) {
            left[i] = array[i];
        }
        return left;
    }

    // Returns the second half of the given array.
    public static int[] rightHalf(int[] array) {
        int size1 = array.length / 2;
        int size2 = array.length - size1;
        int[] right = new int[size2];
        for (int i = 0; i < size2; i++) {
            right[i] = array[i + size1];
        }
        return right;
    }

    // Merges the given left and right arrays into the given
    // result array.  Second, working version.
    // pre : result is empty; left/right are sorted
    // post: result contains result of merging sorted lists;
    public static void merge(int[] result,
                             int[] left, int[] right) {
        int i1 = 0;   // index into left array
        int i2 = 0;   // index into right array

        for (int i = 0; i < result.length; i++) {
            if (i2 >= right.length || (i1 < left.length &&
                    left[i1] <= right[i2])) {
                result[i] = left[i1];    // take from left
                i1++;
            } else {
                result[i] = right[i2];   // take from right
                i2++;
            }
        }
    }
}