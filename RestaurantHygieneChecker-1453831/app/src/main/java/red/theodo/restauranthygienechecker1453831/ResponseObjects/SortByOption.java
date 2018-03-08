package red.theodo.restauranthygienechecker1453831.ResponseObjects;

/**
 * Created by theo on 07/03/18.
 */

public class SortByOption {
    private String name;
    private String key;
    private int id;

    public SortByOption(String name, String key, int id) {
        // API error rectification
        if(key.equals("Distance")) key = "distance";

        this.name = name;
        this.key = key;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
