import java.math.BigInteger;
import java.util.Scanner;

public class Karatsuba {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        BigInteger a = reader.nextBigInteger();
        BigInteger b = reader.nextBigInteger();

        BigInteger result = karatsubaMultiply(a, b);
        System.out.print(ToString(result));
    }

    private static BigInteger karatsubaMultiply(BigInteger x, BigInteger y)
    {
        int n = Math.max(x.bitLength(), y.bitLength());

        if (n <= 1) { return x.multiply(y); }

        BigInteger xl = x.shiftRight(n / 2);
        BigInteger xr = x.subtract(xl.shiftLeft(n / 2));

        BigInteger yl = y.shiftRight(n / 2);
        BigInteger yr = y.subtract(yl.shiftLeft(n / 2));

        BigInteger P1 = karatsubaMultiply(xl, yl);
        BigInteger P2 = karatsubaMultiply(xr, yr);
        BigInteger P3 = karatsubaMultiply(xl.add(xr), yl.add(yr));

        return P1.shiftLeft((n / 2) * 2).add((P3.subtract(P2).subtract(P1)).shiftLeft(n / 2)).add(P2);
    }

    public static String ToString(BigInteger x) {
        BigInteger pows[] = new BigInteger[32];
        pows[0] = BigInteger.valueOf(10);
        int i = 0;
        for (; x.compareTo(pows[i]) >= 0; ++i)
            pows[i + 1] = pows[i].multiply(pows[i]);
        return ToString(x, pows, i - 1, false);
    }

    private static String ToString(BigInteger x, BigInteger[] pows, int k,
                                   boolean needZeros) {
        if (k <= 0) {
            if (needZeros || x.compareTo(BigInteger.ZERO) != 0)
                return x.toString();
            else
                return "";
        }
        int cmp = x.compareTo(BigInteger.ZERO);
        if (!needZeros && cmp == 0)
            return "";
        BigInteger[] parts = x.divideAndRemainder(pows[k]);
        StringBuffer buf = new StringBuffer(ToString(parts[0], pows, k - 1,
                needZeros));
        String tmp = ToString(parts[1], pows, k - 1, needZeros
                || parts[0].compareTo(BigInteger.ZERO) > 0);
        if (needZeros || parts[0].compareTo(BigInteger.ZERO) > 0) {
            StringBuffer[] t = new StringBuffer[32];
            t[0] = new StringBuffer("0");
            int needLen = (1 << k) - tmp.length();
            if (needLen > 0) {
                int j = 0;
                for (int i = needLen; i > 0; i >>= 1) {
                    t[j + 1] = new StringBuffer();
                    t[j + 1].append(t[j].toString());
                    t[j + 1].append(t[j].toString());
                    ++j;
                }
                j = 0;
                while (needLen > 0) {
                    if ((needLen & 1) > 0)
                        buf.append(t[j]);
                    ++j;
                    needLen >>= 1;
                }
            }
        }
        buf.append(tmp);
        return buf.toString();
    }
}