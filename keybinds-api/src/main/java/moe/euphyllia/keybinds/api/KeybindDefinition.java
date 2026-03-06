package moe.euphyllia.keybinds.api;

import java.util.Objects;
import java.util.regex.Pattern;

public final class KeybindDefinition {

    private static final Pattern ID_PATTERN = Pattern.compile("^[a-z0-9_]+:[a-z0-9_]+$");

    private final String id;
    private final int defaultKey;

    private KeybindDefinition(String id, int defaultKey) {
        this.id = id;
        this.defaultKey = defaultKey;
    }

    public static KeybindDefinition of(String id, int defaultKey) {
        validateId(id);
        return new KeybindDefinition(id, defaultKey);
    }

    public static boolean isValidId(String id) {
        return id != null && ID_PATTERN.matcher(id).matches();
    }


    private static void validateId(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Keybind ID cannot be null or blank");
        }
        if (!ID_PATTERN.matcher(id).matches()) {
            throw new IllegalArgumentException(
                    "Invalid keybind ID '" + id + "'. Must match pattern namespace:key (e.g. myplugin:dash)"
            );
        }
    }

    public String getId() {
        return id;
    }

    public int getDefaultKey() {
        return defaultKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KeybindDefinition that)) return false;
        return defaultKey == that.defaultKey && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, defaultKey);
    }
}
