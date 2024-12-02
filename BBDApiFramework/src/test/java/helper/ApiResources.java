package helper;

public enum ApiResources {
    AddPlaceAPI("/maps/api/place/add/json"),
    GetPlaceAPI("/maps/api/place/get/json"),
    UpdatePlaceAPI("/maps/api/place/update/json"),
    DeletePlaceAPI("/maps/api/place/delete/json");

    private String resource;

    ApiResources(String resource){
        this.resource = resource;
    }

    public String getResource(){
        return resource;
    }
}
