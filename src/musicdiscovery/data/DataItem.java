package musicdiscovery.data;

import java.util.UUID;

public abstract class DataItem implements Comparable<DataItem> {
    private final String id;

    public DataItem() {
        id = UUID.randomUUID().toString().replace("-", "");
    }

    public String getId() {
        return id;
    }
}
