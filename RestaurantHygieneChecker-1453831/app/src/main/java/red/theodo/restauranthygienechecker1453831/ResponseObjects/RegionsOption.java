package red.theodo.restauranthygienechecker1453831.ResponseObjects;

/**
 * Created by theo on 08/03/18.
 */

public class RegionsOption {

    private String name;
    private String nameKey;
    private int id;

    public RegionsOption(String name, String nameKey, int id) {
        this.name = name;
        this.nameKey = nameKey;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getNameKey() {
        return nameKey;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }
}
