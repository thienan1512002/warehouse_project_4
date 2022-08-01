package vn.aptech.warehousemobile.api;

import vn.aptech.warehousemobile.api.service.UserService;

public class ApiUtil {
    public static final String BASE_URL ="http://172.16.3.151:8080/api/";

    public static UserService getUserService(){
        return RetroFitClient.getClient(BASE_URL).create(UserService.class);
    }
}
