import java.math.BigInteger;

public class Excercise_13 {

    public static void main(String[] args) {
        int e = 3;
        int bits_1024 = 1024;
        BigInteger m_1024 = computeLengthOfM(bits_1024, e);
        int bits_2048 = 2048;
        BigInteger m_2048 = computeLengthOfM(bits_2048, e);
        int bits_3072 = 3072;
        BigInteger m_3072 = computeLengthOfM(bits_3072, e);
        int bits_4096 = 4096;
        BigInteger m_4096 = computeLengthOfM(bits_4096, e);

        System.out.println("Assuming that e = 3 and ASCII character = 8 Bits");
        print(m_1024, "1024");
        print(m_2048, "2048");
        print(m_3072, "3072");
        print(m_4096, "4096");
    }

    private static BigInteger computeLengthOfM(int exponent, int e) {
        exponent = exponent / e;
        BigInteger two = BigInteger.valueOf(2);
        return two.pow(exponent);
    }

    private static void print(BigInteger num, String length) {
        System.out.println(length + ": " + num);
        System.out.println("This message can have max length of m = " + num.bitLength() + " what results in max " +num.bitLength()/8+ " of ASCII chars.");
    }
}
