package com.kuartz.core.common.util;

import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;

public class EncryptUtils {
    // some random salt
    private static final byte[] SALT = {(byte) 0x21, (byte) 0x21, (byte) 0xF0, (byte) 0x55, (byte) 0xC3, (byte) 0x9F, (byte) 0x5A, (byte) 0x75};

    private final static int ITERATION_COUNT = 31;

    private EncryptUtils() {
    }

    public static String encode(String input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }
        try {

            KeySpec keySpec = new PBEKeySpec(null, "and".getBytes(), ITERATION_COUNT);
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(SALT, ITERATION_COUNT);

            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);

            Cipher ecipher = Cipher.getInstance(key.getAlgorithm());
            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);

            byte[] enc = ecipher.doFinal(input.getBytes());

            return new String(Base64Utils.encodeUrlSafe(enc));

        } catch (Exception e) {
        }

        return "";

    }

    public static String decode(String token) {
        if (token == null) {
            return null;
        }
        try {
            byte[] dec =Base64Utils.decodeFromUrlSafeString(token);
            KeySpec keySpec = new PBEKeySpec(null, "and".getBytes(), ITERATION_COUNT);
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(SALT, ITERATION_COUNT);
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
            Cipher dcipher = Cipher.getInstance(key.getAlgorithm());
            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
            byte[] decoded = dcipher.doFinal(dec);
            return new String(decoded);
        } catch (Exception e) {
            // use logger in production code
            e.printStackTrace();
        }

        return null;
    }
}
