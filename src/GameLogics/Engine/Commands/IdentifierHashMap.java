package GameLogics.Engine.Commands;

import java.util.HashMap;

public class IdentifierHashMap extends HashMap<String, Long> {
    @Override
    public Long get(Object key) {
        if (containsKey(key)) {
            return super.get(key);
        } else {
            return 0L;
        }
    }
}