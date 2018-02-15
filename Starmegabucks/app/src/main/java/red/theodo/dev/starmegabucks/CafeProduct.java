package red.theodo.dev.starmegabucks;

/**
 * Created by tjmg on 15-Feb-18.
 */

public class CafeProduct {
    String name;
    int apiID;

    public CafeProduct(String name, int apiID) {
        this.name = name;
        this.apiID = apiID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getApiID() {
        return apiID;
    }

    public void setApiID(int apiID) {
        this.apiID = apiID;
    }

    @Override
    public String toString() {
        return name;
    }
}
