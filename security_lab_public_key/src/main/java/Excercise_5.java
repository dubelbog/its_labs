import javax.naming.OperationNotSupportedException;
import java.io.*;
import java.math.BigInteger;

public class Excercise_5 {

    public static void main(String[] args) throws IOException, OperationNotSupportedException, ClassNotFoundException {
        StringBuilder input = new StringBuilder();
        BigInteger inputBi;
        if (args.length == 0) {
            String txt = Utils.readFile(Constants.MESSAGE);
            input.append(txt);
            inputBi = new BigInteger(input.toString().getBytes("us-ascii"));
        } else {
            for (String arg : args) {
                input.append(arg).append(" ");
            }
            inputBi = new BigInteger(input.toString().getBytes("us-ascii"));
        }

        System.out.println("das ist der input: " + input);
        Utils.generateKey();
        encrypte(inputBi);
        decrypte();

    }

    private static BigInteger encrypte(BigInteger inputBi) throws IOException, ClassNotFoundException {
        ObjectInputStream publicKey  = new ObjectInputStream(new FileInputStream(Constants.PUBLIC_KEY));
        RSA rsa = new RSA(publicKey);
        BigInteger encrypted = rsa.encrypt(inputBi);
        Utils.writeFile(encrypted, Constants.CIPHER, "");
        return encrypted;
    }

    private static void decrypte() throws IOException, OperationNotSupportedException, ClassNotFoundException {
        ObjectInputStream privateKey  = new ObjectInputStream(new FileInputStream(Constants.PRIVATE_KEY));
        RSA rsa = new RSA(privateKey);
        BufferedReader reader = new BufferedReader(new FileReader(Constants.CIPHER));
        BigInteger encrypted = new BigInteger(reader.readLine());
        BigInteger decrypted = rsa.decrypt(encrypted);
        String solution = new String(decrypted.toByteArray());
        Utils.writeFile(BigInteger.ZERO, Constants.DECRYPTED, solution);
        System.out.println(solution);
    }

}
