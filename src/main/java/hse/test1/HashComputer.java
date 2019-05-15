package hse.test1;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class HashComputer {


    public static Byte[] compute(@NotNull List<String> hashes) throws NoSuchAlgorithmException, IOException {
        var digest = MessageDigest.getInstance("MD5");
        StringBuilder sumString = new StringBuilder();
        for (var hash: hashes) {
            sumString.append(hash);
        }
        var input = new ByteArrayInputStream(sumString.toString().getBytes());
        return compute(new DigestInputStream(input, digest));
    }

    public static Byte[] compute(@NotNull String fileName) throws NoSuchAlgorithmException, IOException {
        var digest = MessageDigest.getInstance("MD5");
        var file = new File(fileName);
        return compute(new DigestInputStream(new FileInputStream(file), digest));
    }

    private static Byte[] compute(DigestInputStream hashInput) throws IOException {
        while (hashInput.read() != -1) {}
        var bytes = hashInput.getMessageDigest().digest();
        var result = new Byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            result[i] = bytes[i];
        }
        return result;
    }
}