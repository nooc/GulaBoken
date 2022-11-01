package yh.gulaboken;

public class StringUtil {
    public static StringBuilder appendToBuilder(StringBuilder builder, String value) {
        if(!value.isEmpty()) {
            if(!builder.isEmpty()) { builder.append(", "); }
            builder.append(value);
        }
        return builder;
    }
}
