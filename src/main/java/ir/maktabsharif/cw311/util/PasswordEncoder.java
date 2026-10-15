package ir.maktabsharif.cw311.util;

import com.password4j.BcryptFunction;
import com.password4j.Password;
import com.password4j.types.Bcrypt;

public class PasswordEncoder {
    public static String encodePass(String password) {
        BcryptFunction bcryptFunction = BcryptFunction.getInstance(Bcrypt.B, 12);
        return Password.hash(password).addPepper().with(bcryptFunction).getResult();
    }

    public static boolean validatePass(String password, String hashPassword) {
        BcryptFunction bcryptFunction = BcryptFunction.getInstance(Bcrypt.B, 12);
        return Password.check(password, hashPassword).addPepper().with(bcryptFunction);
    }
}
