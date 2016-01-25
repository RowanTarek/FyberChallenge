package com.ardev.assessment.fyberchallenge.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by RowanTarek on 25/01/2016.
 * @author  <a href="http://stackoverflow.com/users/419075/amir-raminfar">Amir Raminfar</a>
 */

public class SHA1Generator {

    private static String convertToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (byte b : data) {
            int halfbyte = (b >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                buf.append((0 <= halfbyte) && (halfbyte <= 9) ? (char) ('0' + halfbyte) : (char) ('a' + (halfbyte - 10)));
                halfbyte = b & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }//end convertToHex
    /*****************************************************************************/

    public static String generateStringHash(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] bytes = text.getBytes();
            md.update(bytes, 0, bytes.length);
            byte[] sha1hash = md.digest();
            return convertToHex(sha1hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }//end generateRequestHash
}//end class SHA1Generator
