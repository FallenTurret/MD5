package hse.test1;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Type file path");
            return;
        }
        var time = System.currentTimeMillis();
        var resultOne = CheckSumComputerOneThread.compute(args[0]);
        var elapsedTimeOne = System.currentTimeMillis() - time;
        time = System.currentTimeMillis();
        var resultMany = CheckSumComputerOneThread.compute(args[0]);
        var elapsedTimeMany = System.currentTimeMillis() - time;
        if (resultOne != null) {
            System.out.println("One thread result: " + resultOne);
        }
        System.out.println("One thread time: " + elapsedTimeOne);
        if (resultMany != null) {
            System.out.println("Many threads result: " + resultMany);
        }
        System.out.println("Many threads time: " + elapsedTimeMany);
    }
}