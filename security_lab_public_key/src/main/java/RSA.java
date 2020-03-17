import org.eclipse.jetty.http.BadMessageException;

import javax.naming.OperationNotSupportedException;
import java.io.*;
import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA
{
    private static BigInteger one = BigInteger.ONE;
    private BigInteger p;
    private BigInteger q;
    private BigInteger n;
    private BigInteger e;
    private BigInteger d;
    private BigInteger phi;
    private boolean onlyEncryptMode;

    private final static SecureRandom random = new SecureRandom();

    public RSA(Integer bitlengthQ, Integer q_keylength) {
        p = BigInteger.probablePrime(bitlengthQ, random);
        q = BigInteger.probablePrime(q_keylength, random);

        n = p.multiply(q);

        phi = calculatePhi();
        e = new BigInteger("65537");
        d = calculateD();
    }

    public RSA(ObjectInputStream is) throws IOException, ClassNotFoundException {
        n = (BigInteger) is.readObject();
        e = (BigInteger) is.readObject();

        try {
            d = (BigInteger) is.readObject();
        } catch (IOException e) {
            onlyEncryptMode = true;
        }
    }

    public BigInteger encrypt(BigInteger plain) throws BadMessageException {
        if (plain.compareTo(one) < 0 || plain.compareTo(n.subtract(one)) > 0) {
            throw new BadMessageException();
        } else {
            return plain.modPow(e,n);
        }
    }

    public BigInteger decrypt(BigInteger cipher) throws IllegalArgumentException, OperationNotSupportedException {
        if (onlyEncryptMode) {
            throw new OperationNotSupportedException();
        } else if (cipher.compareTo(one) < 0 || cipher.compareTo(n.subtract(one)) > 0) {
            throw new BadMessageException();
        } else {
            return cipher.modPow(d,n);
        }
    }

    public void save(ObjectOutputStream os) throws IOException {
        os.writeObject(n);
        os.writeObject(e);
        os.writeObject(d);
    }

    public void savePublic(ObjectOutputStream os) throws IOException {
        os.writeObject(n);
        os.writeObject(e);
    }

    public BigInteger sign(BigInteger message) throws IllegalArgumentException, OperationNotSupportedException {
        if (onlyEncryptMode) {
            throw new OperationNotSupportedException();
        }  else {
            return message.modPow(d,n);
        }
    }

    public boolean verify(BigInteger message, BigInteger signature) throws IllegalArgumentException {
        BigInteger messageDf = signature.modPow(e,n);
        return message.compareTo(messageDf) == 0;
    }

    private BigInteger calculatePhi() {
        return p.subtract(one).multiply(q.subtract(one));
    }

    private BigInteger calculateD() {
        // e * d mod(phi)
        return e.modInverse(phi);
    }

}