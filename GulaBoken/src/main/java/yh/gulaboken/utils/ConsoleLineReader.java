package yh.gulaboken.utils;

import yh.gulaboken.ILineReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleLineReader implements ILineReader {
    private static final int ARBITRARY_SPLIT = 0;
    private static final String SPLIT_PATTERN = "\\s+";
    private final Scanner scanner;

    /**
     * Constructor
     * Create scanner instance.
     */
    public ConsoleLineReader() {
        scanner = new Scanner(System.in);
    }

    @Override
    public List<String> getLine(int splitLimit) {
        // read trimmed line from scanner and split it into max 'splitLimit' parts.
        var line = scanner.nextLine().trim().split(SPLIT_PATTERN, splitLimit);
        // return as modifiable list
        return new ArrayList<>(List.of(line));
    }

    @Override
    public List<String> getLine() {
        // split with no limit
        return getLine(ARBITRARY_SPLIT);
    }
}
