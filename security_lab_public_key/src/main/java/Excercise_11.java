import javax.naming.OperationNotSupportedException;
import java.io.*;
import java.math.BigInteger;

public class Excercise_11 {

    private static BigInteger inputBi;

    public static void main(String[] args) throws IOException, OperationNotSupportedException, ClassNotFoundException {
        StringBuilder input = new StringBuilder();
        if (args.length == 0) {
            String txt = Utils.readFile(Constants.SIGNATURE_MESSAGE);
            input.append(txt);
            inputBi = new BigInteger(input.toString().getBytes("us-ascii"));
        } else {
            for (String arg : args) {
                input.append(arg).append(" ");
            }
            inputBi = new BigInteger(input.toString().getBytes("us-ascii"));
        }

        Utils.generateKey();
        sign(inputBi);
        verify();

    }

    private static BigInteger sign(BigInteger inputBi) throws IOException, ClassNotFoundException, OperationNotSupportedException {
        ObjectInputStream publicKey  = new ObjectInputStream(new FileInputStream(Constants.PRIVATE_KEY));
        RSA rsa = new RSA(publicKey);
        BigInteger signature = rsa.sign(inputBi);
        Utils.writeFile(signature, Constants.SIGNATURE, "");
        return signature;
    }

    private static void verify() throws IOException, ClassNotFoundException {
        ObjectInputStream privateKey  = new ObjectInputStream(new FileInputStream(Constants.PUBLIC_KEY));
        RSA rsa = new RSA(privateKey);
        BufferedReader reader = new BufferedReader(new FileReader(Constants.SIGNATURE));
        BigInteger signature = new BigInteger(reader.readLine());
        boolean verificationPassed = rsa.verify(inputBi, signature);
        System.out.println("Does verification passed? ");
        System.out.println(verificationPassed);
    }
}
