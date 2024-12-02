package helper;

import pojo.AddPlace;
import pojo.LocationCoordinates;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuilder {

    public static AddPlace getAddPlacePayload(String name, String language, String address) {
        AddPlace p = new AddPlace();
        LocationCoordinates lc = new LocationCoordinates();
        lc.setLat(-38.483494);
        lc.setLng(33.527362);

        List<String> types = new ArrayList<>();
        types.add("shoe park");
        types.add("shop");

        p.setLocation(lc);
        p.setAccuracy(50);
        p.setName(name);
        p.setPhone_number("(+91) 983 893 3937");
        p.setAddress(address);
        p.setTypes(types);
        p.setWebsite("http://blabla.com");
        p.setLanguage(language);

        return p;
    }

    public static String deletePlacePayload(String placeId){

        return "{\n" +
                "    \"place_id\":\""+ placeId +"\"\n" +
                "}\n";
    }
}
