package hse.test1;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.concurrent.RecursiveTask;

public class CheckSumComputer extends RecursiveTask<Byte[]> {

    private final String path;

    public CheckSumComputer(@NotNull String path) {
        this.path = path;
    }

    @Override
    protected Byte[] compute() {
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
        var subTasks = new LinkedList<CheckSumComputer>();
        for (int i = 0; i < subpaths.length; i++) {
            var task = new CheckSumComputer(subpaths[i]);
            task.fork();
            subTasks.add(task);
        }
        var hashes = new LinkedList<String>();
        hashes.add(dir.getName());
        for (var task: subTasks) {
            hashes.add(Arrays.toString(task.join()));
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