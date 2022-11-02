package yh.gulaboken.models;

public abstract class BaseModel {
    /**
     * Make sure value is non-null.
     * @param value Input value.
     * @return Input value if not null, else empty string.
     */
    protected String nonNull(String value) {
        return value == null ? "" : value;
    }
}
