package hse.test1;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

public class CheckSumComputerOneThread {

    public static Byte[] compute(@NotNull String path) {
        var dir = new File(path);
        if (dir.isFile()) {
            try {
                return HashComputer.compute(path);
            } catch (NoSuchAlgorithmException e) {
                System.out.println("You don't have MD5 algorithm");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String[] subpaths = (String[]) Arrays.stream(Objects.requireNonNull(dir.listFiles()))
                .map(File::getAbsolutePath).toArray();
        var hashes = new LinkedList<String>();
        hashes.add(dir.getName());
        for (var subpath: subpaths) {
            hashes.add(Arrays.toString(compute(path)));
        }

        try {
            return HashComputer.compute(hashes);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("You don't have MD5 algorithm");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}