package red.theodo.restauranthygienechecker1453831;

/**
 * Created by theo on 08/03/18.
 */

public enum SearchMode {
    Local(100),
    Simple(101),
    Advanced(102);

    private final int id;

    SearchMode(int id) {
        this.id = id;
    }

    public int getValue() {
        return id;
    }
}
