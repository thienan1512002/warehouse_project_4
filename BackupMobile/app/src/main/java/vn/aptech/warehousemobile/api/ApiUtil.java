package vn.aptech.warehousemobile.api;

import vn.aptech.warehousemobile.api.service.AllocateService;
import vn.aptech.warehousemobile.api.service.GoodsService;
import vn.aptech.warehousemobile.api.service.IssueOrderService;
import vn.aptech.warehousemobile.api.service.LocationService;
import vn.aptech.warehousemobile.api.service.TransactionService;
import vn.aptech.warehousemobile.api.service.UserService;

public class ApiUtil {
    public static final String BASE_URL ="http://192.168.1.3:8080/api/";
    public static final String IMG_URL ="http://192.168.1.3:8080/goods-photos/";
//    public static final String BASE_URL ="http://192.168.1.12:8080/api/";
//    public static final String IMG_URL ="http://192.168.1.12:8080/goods-photos/";

    public static UserService getUserService(){
        return RetroFitClient.getClient(BASE_URL).create(UserService.class);
    }

    public static AllocateService getAllocateService(){
        return RetroFitClient.getClient(BASE_URL).create(AllocateService.class);
    }

    public static LocationService getLocationService(){
        return RetroFitClient.getClient(BASE_URL).create(LocationService.class);
    }

   public static IssueOrderService getIssueOrderService(){
        return RetroFitClient.getClient(BASE_URL).create(IssueOrderService.class);
   }
   public static GoodsService getGoodsDataService(){
        return RetroFitClient.getClient(BASE_URL).create(GoodsService.class);
   }
    public static TransactionService getTransactionService(){
        return RetroFitClient.getClient(BASE_URL).create(TransactionService.class);
   }

}
