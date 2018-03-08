package red.theodo.restauranthygienechecker1453831.ResponseObjects;

/**
 * Created by theo on 08/03/18.
 */

public class AuthoritiesOption {
    private String name;
    private int id;

    public AuthoritiesOption(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }
}
