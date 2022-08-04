package vn.aptech.warehousemobile.api;

import vn.aptech.warehousemobile.api.service.UserService;

public class ApiUtil {
    public static final String BASE_URL ="http://192.168.1.5:8080/api/";

    public static UserService getUserService(){
        return RetroFitClient.getClient(BASE_URL).create(UserService.class);
    }
}
