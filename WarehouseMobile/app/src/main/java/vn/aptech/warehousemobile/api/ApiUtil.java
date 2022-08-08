package vn.aptech.warehousemobile.api;

import vn.aptech.warehousemobile.api.service.AllocateService;
import vn.aptech.warehousemobile.api.service.LocationService;
import vn.aptech.warehousemobile.api.service.UserService;

public class ApiUtil {
    //public static final String BASE_URL ="http://192.168.1.5:8080/api/";
    public static final String BASE_URL ="http://192.168.1.6:8080/api/";
    public static UserService getUserService(){
        return RetroFitClient.getClient(BASE_URL).create(UserService.class);
    }

    public static AllocateService getAllocateService(){
        return RetroFitClient.getClient(BASE_URL).create(AllocateService.class);
    }

    public static LocationService getLocationService(){
        return RetroFitClient.getClient(BASE_URL).create(LocationService.class);
    }
}
