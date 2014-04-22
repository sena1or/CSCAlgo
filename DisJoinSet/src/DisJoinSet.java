import java.io.*;
import java.util.StringTokenizer;

public class DisJoinSet{
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

	private static Element elements[];

	private static void solve() throws NumberFormatException, IOException
	{
		int elementNumber = nextInt();
		int commands = nextInt();

		elements = new Element[elementNumber];

		for (int i = 0; i < elementNumber; i++)
		{
			elements[i] = new Element(i);
		}

		for (int i = 0; i < commands; i++)
		{
			String command = nextToken();
			if (command.contentEquals("Check"))
			{
				out.println(Check(nextInt() - 1, nextInt() - 1) ? "True" : "False");
			}

			if (command.contentEquals("Union"))
			{
				Union(nextInt() - 1, nextInt() - 1);
			}
		}
	}

	private static void Union(int x, int y)
	{
		Element rx = Find(x);
		Element ry = Find(y);

		if (rx.equals(ry))
		{
			return;
		}

		int rankX = rx.getRank();
		int rankY = rx.getRank();

		if (rankX > rankY)
		{
			ry.setParent(rx);
		}
		else
		{
			rx.setParent(ry);

			if (rankX == rankY)
			{
				rx.setRank(rankY + 1);
			}
		}
		return;
	}

	private static Element Find(int x)
	{
		if (!elements[x].getParent().equals(elements[x]))
		{
			elements[x].setParent(Find(elements[x].getParent().getValue()));
		}

		return elements[x].getParent();
	}

	private static boolean Check(int x, int y)
	{
		if (Find(x).equals(Find(y)))
		{
			return true;
		}

		return false;
	}
}

class Element
{
	private int value;

	private int rank;
	private Element parent;

	public Element(int v)
	{
		this.rank = 0;
		this.value = v;
		this.parent = this;
	}

	public int getValue()
	{
		return this.value;
	}

	public Element getParent()
	{
		return  this.parent;
	}

	public void setParent(Element e)
	{
		this.parent = e;
	}

	public int getRank()
	{
		return this.rank;
	}

	public void setRank(int r)
	{
		this.rank = r;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Element)
		{
			return this.value == ((Element) obj).value;
		}

		return false;
	}
}