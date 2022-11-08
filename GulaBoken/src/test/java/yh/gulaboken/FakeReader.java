package yh.gulaboken;

import java.util.ArrayList;
import java.util.List;

class FakeReader implements ILineReader {
    private static final int ARBITRARY_SPLIT = 0;
    private static final String SPLIT_PATTERN = "\\s+";

    private int currentLine;
    private String[] lines;

    FakeReader(String[] lines) {
        this.currentLine = 0;
        this.lines = lines;
    }

    @Override
    public List<String> getLine(int splitLimit) {
        return new ArrayList<>(List.of(nextLine().split(SPLIT_PATTERN, splitLimit)));
    }

    @Override
    public List<String> getLine() {
        return getLine(ARBITRARY_SPLIT);
    }

    private String nextLine() {
        if (currentLine >= lines.length) {
            throw new RuntimeException("Out of commands.");
        }
        System.out.println(lines[currentLine]);
        return lines[currentLine++];
    }
}
