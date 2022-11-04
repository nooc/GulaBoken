package yh.gulaboken;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Line reader interface.
 */
public interface ILineReader {
    /**
     * Get line from console, split into max `splitLimit` parts by whitespace pattern.
     *
     * @param splitLimit split limit
     * @return command line as list
     */
    @NotNull
    List<String> getLine(int splitLimit);

    /**
     * Get line from console split into parts by whitespace pattern.
     *
     * @return command line as list
     */
    @NotNull
    List<String> getLine();
}
