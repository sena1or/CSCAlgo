import java.util.Arrays;
import java.util.Scanner;

public class Heap {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int commandCount = reader.nextInt();
        BinaryHeap heap = new BinaryHeap(commandCount);

        for (int i = 0; i < commandCount; i++)
        {
            String command = reader.next();

            if (command.contentEquals("Insert"))
            {
                int element = reader.nextInt();
                heap.Insert(element);
            }

            if (command.contentEquals("Extract"))
            {
                System.out.println(heap.Extract());
            }
        }
    }
}

class BinaryHeap {
    private int[] heap;
    private int count;

    public BinaryHeap(int capacity)
    {
        this.heap = new int[HighestPowerOfTwo(capacity)];
		Arrays.fill(this.heap, Integer.MIN_VALUE);
        this.count = 0;
    }

    public void Insert(int element)
    {
        this.heap[count] = element;
        SiftUp(count);
        count++;
    }

    private void SiftUp(int index) {
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

    public int Extract()
    {
        int result = heap[0];
        count--;
        heap[0] = heap[count];
		heap[count] = Integer.MIN_VALUE;
        SiftDown(0);
        return result;
    }

    private void SiftDown(int index)
	{
		while (index * 2 + 1 < this.heap.length)
		{
			int maxChildNumber;

			if (heap[2 * index + 1] < heap[2 * index + 2])
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

	private int HighestPowerOfTwo(int v)
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