import java.io.*;
import java.math.BigInteger;

public class Utils {

    public static void generateKey() throws IOException {
        RSA rsa = new RSA(1015, 1033);
        ObjectOutputStream publicKey  = new ObjectOutputStream(new FileOutputStream(Constants.PUBLIC_KEY));
        ObjectOutputStream privateKey  = new ObjectOutputStream(new FileOutputStream(Constants.PRIVATE_KEY));
        rsa.save(privateKey);
        rsa.savePublic(publicKey);
    }

    public static void writeFile(BigInteger input, String filename, String inputStr) {
        File file = new File(filename);
        try {
            PrintWriter writer = new PrintWriter(file);
            if(inputStr.equals("")){
                writer.println(input);
            } else {
                writer.println(inputStr);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFile(String filename) throws IOException {
        File file = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String output;
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            output = sb.toString();
        } finally {
            br.close();
        }
        return output;
    }
}
