package red.theodo.restauranthygienechecker1453831;

/**
 * Created by theo on 06/03/18.
 */

public class SearchBuilder {
    boolean a_advanced = false;
    String a_query = "";
    String a_type = "";
    String a_rating = "";
    String a_region = "";
    String a_localAuthority = "";

    SearchBuilder advanced(boolean a){
        a_advanced = a;
        return this;
    }

    SearchBuilder query(String a){
        a_query = a;
        return this;
    }

    SearchBuilder type(String a){
        a_type = a;
        return this;
    }

    SearchBuilder rating(String a){
        a_rating = a;
        return this;
    }

    SearchBuilder region(String a){
        a_region = a;
        return this;
    }

    SearchBuilder localAuthority(String a){
        a_localAuthority = a;
        return this;
    }

    SearchDetails build(){
        return new SearchDetails(a_advanced, a_query, a_type, a_rating, a_region, a_localAuthority);
    }
}
