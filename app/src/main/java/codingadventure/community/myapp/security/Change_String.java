package codingadventure.community.myapp.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Change_String {

   /* *//**패스워드 해싱용*//*
    public static String hashString(String message, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        byte[] hashedBytes = digest.digest(message.getBytes());
        StringBuilder stringBuffer = new StringBuilder();
        for (byte b : hashedBytes) {
            stringBuffer.append(String.format("%02x", b));
        }
        return stringBuffer.toString();
    }*/



}
