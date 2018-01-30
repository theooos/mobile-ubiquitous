package dev.theodo.red.coffeerecomender;

import java.util.ArrayList;

/**
 * Created by theo on 30/01/2018.
 */

class CoffeeExpert {

    ArrayList<String> getBrands(String strength){
        switch (strength) {
            case "Strong":
                return new ArrayList<String>() {{
                    add("strong 1");
                    add("strong 2");
                    add("strong 3");
                }};
            case "Medium":
                return new ArrayList<String>() {{
                    add("medium 1");
                    add("medium 2");
                    add("medium 3");
                }};
            default:
                return new ArrayList<String>() {{
                    add("weak 1");
                    add("weak 2");
                    add("weak 3");
                }};
        }
    }
}
