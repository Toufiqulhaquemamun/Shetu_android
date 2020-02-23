package com.example.amir.shetu.retorfit;

import com.example.amir.shetu.model.AgentBuyerList;
import com.example.amir.shetu.model.AgentComission;
import com.example.amir.shetu.model.AgentSellerList;
import com.example.amir.shetu.model.BiddedCommodityPostList;
import com.example.amir.shetu.model.BidsOfSeller;
import com.example.amir.shetu.model.Commodity;
import com.example.amir.shetu.model.CommodityGrade;
import com.example.amir.shetu.model.DeliverableList;
import com.example.amir.shetu.model.DeviceList;
import com.example.amir.shetu.model.District;
import com.example.amir.shetu.model.PackageList;
import com.example.amir.shetu.model.PdffileList;
import com.example.amir.shetu.model.PreOrder;
import com.example.amir.shetu.model.PreOrderCommodityPost;
import com.example.amir.shetu.model.PreorderList;
import com.example.amir.shetu.model.PreorderOrderList;
import com.example.amir.shetu.model.ProfileSME;
import com.example.amir.shetu.model.ReceiveableList;
import com.example.amir.shetu.model.Registration;
import com.example.amir.shetu.model.SMEList;
import com.example.amir.shetu.model.SMECommodityList;
import com.example.amir.shetu.model.SaleableProductBidDetails;
import com.example.amir.shetu.model.AllBidPrice;
import com.example.amir.shetu.model.Authenticate;
import com.example.amir.shetu.model.Bid;
import com.example.amir.shetu.model.CommodityPostDetail;
import com.example.amir.shetu.model.CommodityPostList;
import com.example.amir.shetu.model.SellHistoryDetail;
import com.example.amir.shetu.model.ShippingCostByCommodityId;
import com.example.amir.shetu.model.TradeList;
import com.example.amir.shetu.model.Unit;
import com.example.amir.shetu.model.Upazila;
import com.example.amir.shetu.model.UserLogin;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {
    @POST("api/Auth/authenticate")
    Call<Authenticate> login(@Body UserLogin user);

    @POST("api/sme/product/BidProduct")
    Call<Boolean> postBid(@Body Bid bid);

    @POST("api/sme/product/WithdrawProductBid/")
    Call<Boolean> withdrawBid(@Body int bidId);

    @GET("api/sme/product/PurchaseDetails/{productId}/{userId}")
    Call<CommodityPostDetail> getPurchaseBidDetails(@Path("productId") int productId, @Path("userId") int userId);

    @GET("api/sme/product/SaleableProductBidDetails/{productId}")
    Call<SaleableProductBidDetails> getSaleableProductBidDetails(@Path("productId") int productId);

    @GET("api/sme/Home/Profile/{userId}")
    Call<ProfileSME> getProfileInfo(@Path("userId") int userId);

    @GET("/api/sme/product/GetUnits")
    Call<List<Unit>> getUnit();

    @GET("/api/sme/product/getAllBidPriceByProductId/{productId}")
    Call<List<AllBidPrice>> getAllBid(@Path("productId") int productId);

    @Multipart
    @POST("api/sme/product/UploadMoneyReciept")
    Call<ResponseBody> uploadMoneyReciept(@Part("bidId") RequestBody bidId,@Part("remarks") RequestBody remarks,@Part MultipartBody.Part reciept);

    @Multipart
    @POST("api/sme/product/Sale")
    Call<ResponseBody> uploadProduct(
            @Part("quantity") RequestBody Quantity,
            @Part("price") RequestBody Price,
            @Part("unitId") RequestBody UnitId,
            @Part("SMEAssaignedCommodityId") RequestBody ProductId,
            @Part("sellerId") RequestBody SellerId,
            @Part("features") RequestBody Features,
            @Part List<MultipartBody.Part> files);

    @FormUrlEncoded
    @POST("api/sme/product/ChangeBidStatus")
    Call<Boolean> shippingChange(@Field("bidId") int bidId,@Field("status") String status );


    @POST("api/sme/product/ModifyProductBid")
    Call<Boolean> postModifiedBid(@Body Bid bid);

    @POST("api/sme/product/ChangeStatus")
    Call<Boolean> changeStatus(@Body int  bidId);

    @GET("api/sme/product/GetBidInformation/{bidId}")
    Call<Bid> getBidInformation(@Path("bidId") int bidId);

    @GET("api/sme/Home/products/{currentPage}")
    Call<CommodityPostList> getHomeProduct(@Path("currentPage") int currentPage);

    @GET("api/sme/product/detail/{productId}/{userId}")
    Call<CommodityPostDetail> getHomeProductDetails(@Path("productId") int productId, @Path("userId") int userId);

    @GET("/api/sme/product/GetAssignedCommodities/{userId}")
    Call<List<SMECommodityList>> getAssignedCommodities(@Path("userId") int userId);

    @GET("/api/sme/product/SaleableProductList/{page}/{userId}")
    Call<CommodityPostList> getSaleBids(@Path("page") int page,@Path("userId") int userId);

    @GET("/api/sme/product/SaleableProductList/{page}/{userId}")
    Call<List<CommodityPostList.Data>> getSaleBidsTemp(@Path("page") int page,@Path("userId") int userId);

    @GET("/api/sme/Product/{userId}")
    Call<List<SMECommodityList>> getAssignedProductList(@Path("userId") int userId);

    @GET("/api/sme/Product/PurchaseProductList/{page}/{userId}")
    Call<List<BiddedCommodityPostList>> getPurchaseBids(@Path("page") int page, @Path("userId") int userId);

    @GET("/api/sme/Product/SoldCommodityList/{page}/{userId}")
    Call<List<SellHistoryDetail>> getSoldProduct(@Path("page") int page, @Path("userId") int userId);

    @GET("/api/sme/Product/PurchasedCommodityList/{page}/{userId}")
    Call<List<SellHistoryDetail>> getPuschasedProduct(@Path("page") int page, @Path("userId") int userId);

    @GET("/api/sme/Product/bidproduct/{productId}/{userId}")
    Call<Bid> getBidsProductDetails(@Path("productId") int productId, @Path("userId") int userId);

    @GET("api/agent/sme/List/{userId}/{currentPage}")
    Call<SMEList> getSMEList(@Path("userId") int userId, @Path("currentPage") int currentPage);

    @GET("api/agent/trade/index/{agentId}/{currentPage}")
    Call<TradeList>getTradeList(@Path("agentId") int agentId, @Path("currentPage") int currentPage);

    @FormUrlEncoded
    @POST("api/agent/Trade/ChangeBidStatus")
    Call<Boolean> ChangeBidStatus(@Field("bidId") int bidId,@Field("status") String status);

    @GET("api/sme/commodity/List")
    Call<List<PdffileList>>List();

    @GET("api/notification/GetTokens/{userId}")
    Call<List<DeviceList>> getDeviceList(@Path("userId") int userId);

    @GET("/Agent/Trade/ReceivableCommodity/{agentId}/{currentPage}")
    Call<ReceiveableList> getReceiveablList(@Path("agentId") int agentId, @Path("currentPage") int currentPage);

    @GET("/Agent/Trade/DeliverableCommodity/{agentId}/{currentPage}")
    Call<DeliverableList> getDeliverablelList(@Path("agentId") int agentId, @Path("currentPage") int currentPage);

    @FormUrlEncoded
    @POST("api/agent/trade/VerifyAndSell")
    Call<Boolean> verifySell(@Field("bidId") int bidId, @Field("token") String token);

    @GET("api/AgentBuyAndSellApi/AgentBuyerList/{agentId}/{currentPage}")
    Call<AgentSellerList> getAgentSellerList(@Path("agentId") int agentId, @Path("currentPage") int currentPage);

    @GET("api/AgentBuyAndSellApi/AgentSellerList/{agentId}/{currentPage}")
    Call<AgentBuyerList> getAgentBuyerList(@Path("agentId") int agentId, @Path("currentPage") int currentPage);

    @GET("api/CommissionApi/GetCommissionList/{agentId}")
    Call<List<AgentComission>> getAgentComissionList(@Path("agentId") int agentId);

    @GET("/api/sme/preorder/index/{currentPage}")
    Call<PreorderList> getPreorderList(@Path("currentPage") int currentPage);

    @GET("/api/sme/preorder/GetOrderDetail/{id}/{userId}")
    Call<CommodityPostDetail> getPreorderPostDetail( @Path("id") int id,@Path("userId") int userId);

    @POST("/api/sme/preorder/ModifyCommodityBid")
    Call<Boolean> PostModifiedPreOrderBid(@Body Bid bid);

    @POST("/api/sme/preorder/WithdrawCommodityBid")
    Call<Boolean> withdrawPreOrderBid(@Body int id );

    @GET("/api/Information/Districts")
    Call<List<District>>DistrictList();

    @GET("/api/Information/GetUpazilasByDistrictId/{id}")
    Call<List<Upazila>> getUpazilasByDistrictId(@Path("id") int id) ;

    @GET("/api/sme/preorder/Commodities")
    Call<List<Commodity>> getCommodity();

    @GET("admin/commoditygrade/getcommoditygrade/{id}")
    Call<List<CommodityGrade>> getCommodityGradeById(@Path("id") int id) ;

    @GET("/api/sme/preorder/OrderList/{id}")
    Call<List<PreorderOrderList>> getOrderList(@Path("id") int id);

    @GET("/api/sme/preorder/BidsOfSeller/{id}")
    Call<List<BidsOfSeller>> getBidsofSeller(@Path("id") int id);

    @FormUrlEncoded
    @POST("/api/sme/preorder/ChangeSellingStatus/{orderId}/{status}")
    Call<Boolean> changePreOrderBidStatus(@Field("orderId") int orderId,@Field("status") int status);

    @POST("/api/sme/preorder/PostPreorder")
    Call<Boolean> PostPreorder(@Body PreOrderCommodityPost preOrder);

    @GET("/api/sme/preorder/BidDetails/{bidId}")
    Call<BidsOfSeller> getPreorderBidDetails(@Path("bidId") int bidId);

    @POST("api/sme/product/SendToken")
    Call<Boolean> sendSmsToken(@Body int id);

    @Multipart
    @POST("/api/sme/preorder/Bid")
    Call<Boolean> PreorderPostBid( @Part("quantity")RequestBody quantityBody,
                                   @Part("price")RequestBody priceBody,
                                   @Part("vat")RequestBody vatBody,
                                   @Part("serviceCharge")RequestBody serviceChargeBody,
                                   @Part("preOrderId")RequestBody preOrderIdBody,
                                   @Part("sellerId")RequestBody sellerIdBody,
                                   @Part("shippingCharge")RequestBody shipmentChageBody,
                                   @Part List<MultipartBody.Part> images);

    @POST("/api/sme/guset/Register")
    Call<Boolean> guestRegister(@Body Registration guestRegister);


    @GET("/api/sme/GetShippingCostByCommodityId/{id}")
    Call<ShippingCostByCommodityId> getShippingCost(@Path("id") int id);

    @GET("api/sme/PackageListByCommodityId/{commodityId}")
    Call<List<PackageList>> getPackageList(@Path("commodityId") int commodityId);
}
