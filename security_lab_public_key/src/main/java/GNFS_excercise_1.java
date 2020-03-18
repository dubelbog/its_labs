
public class GNFS_excercise_1 {

    public static void main(String[] args) {
        double n_1 = 128.0;
        double n_2 = 256.0;
        double n_3 = 384.0;
        double n_4 = 512.0;
        calculateB(n_1);
        calculateB(n_2);
        calculateB(n_3);
        calculateB(n_4);
    }

    private static int calculateB(double n) {
        int b = 1760;

        double min = Math.pow(2, n);
        double W = Math.exp(1.92 * Math.pow(b, 1.0/3.0) * Math.pow(Math.log(b), 2.0/3.0));

        while (W < min) {
            b++;
            W = Math.exp(1.92 * Math.pow(b, 1.0/3.0) * Math.pow(Math.log(b), 2.0/3.0));
        }

        System.out.println(min + " => 2^" + n );
        System.out.println(W + " => exp(1.92*b^1/3*(ln(b))^2/3)");
        System.out.println("Searched b is: " + b);
        System.out.println("------------------------------------------------------");
        System.out.println();

        return b;
    }
}
