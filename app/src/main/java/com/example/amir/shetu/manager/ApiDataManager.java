package com.example.amir.shetu.manager;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.amir.shetu.R;
import com.example.amir.shetu.interfaces.ApiDataManager.AgentBuyerListListener;
import com.example.amir.shetu.interfaces.ApiDataManager.AgentComissionListListener;
import com.example.amir.shetu.interfaces.ApiDataManager.AgentSellerListListener;
import com.example.amir.shetu.interfaces.ApiDataManager.AssignedProductListener;
import com.example.amir.shetu.interfaces.ApiDataManager.BidInformationListener;
import com.example.amir.shetu.interfaces.ApiDataManager.DeliverableListListener;
import com.example.amir.shetu.interfaces.ApiDataManager.DeviceTokenListener;
import com.example.amir.shetu.interfaces.ApiDataManager.HomeProductDetailsListener;
import com.example.amir.shetu.interfaces.ApiDataManager.HomeProductListener;
import com.example.amir.shetu.interfaces.ApiDataManager.PostBidListener;
import com.example.amir.shetu.interfaces.ApiDataManager.PostModifiedBidListener;
import com.example.amir.shetu.interfaces.ApiDataManager.PostModifiedPreOrderbidListener;
import com.example.amir.shetu.interfaces.ApiDataManager.PreOrderOrderListListener;
import com.example.amir.shetu.interfaces.ApiDataManager.PreOrderProductDetailsListener;
import com.example.amir.shetu.interfaces.ApiDataManager.PreorderListener;
import com.example.amir.shetu.interfaces.ApiDataManager.PreorderPostBidListener;
import com.example.amir.shetu.interfaces.ApiDataManager.ProductGradeListener;
import com.example.amir.shetu.interfaces.ApiDataManager.ProfileInformationListener;
import com.example.amir.shetu.interfaces.ApiDataManager.PurchaseBidsListener;
import com.example.amir.shetu.interfaces.ApiDataManager.PurchasedProductListener;
import com.example.amir.shetu.interfaces.ApiDataManager.ReceiveableListListener;
import com.example.amir.shetu.interfaces.ApiDataManager.SMEListListenter;
import com.example.amir.shetu.interfaces.ApiDataManager.SaleBidsListener;
import com.example.amir.shetu.interfaces.ApiDataManager.SoldProductListener;
import com.example.amir.shetu.interfaces.ApiDataManager.TradeListListener;
import com.example.amir.shetu.interfaces.ApiDataManager.WithdrawBidStatusListener;
import com.example.amir.shetu.model.AgentBuyerList;
import com.example.amir.shetu.model.AgentComission;
import com.example.amir.shetu.model.AgentSellerList;
import com.example.amir.shetu.model.BiddedCommodityPostList;
import com.example.amir.shetu.model.CommodityPostDetail;
import com.example.amir.shetu.model.DeliverableList;
import com.example.amir.shetu.model.DeviceList;
import com.example.amir.shetu.model.PdffileList;
import com.example.amir.shetu.model.PreorderList;
import com.example.amir.shetu.model.PreorderOrderList;
import com.example.amir.shetu.model.ReceiveableList;
import com.example.amir.shetu.model.SMEList;
import com.example.amir.shetu.model.SMECommodityList;
import com.example.amir.shetu.model.TradeList;
import com.example.amir.shetu.other.ApplicationContext;
import com.example.amir.shetu.other.InternetConnection;
import com.example.amir.shetu.retorfit.API;
import com.example.amir.shetu.retorfit.RetrofitInstance;
import com.example.amir.shetu.model.Authenticate;
import com.example.amir.shetu.model.Bid;
import com.example.amir.shetu.model.CommodityPostList;
import com.example.amir.shetu.model.ProfileSME;
import com.example.amir.shetu.model.SellHistoryDetail;
import com.example.amir.shetu.model.UserLogin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiDataManager extends InternetConnection {

    private static MultipartBody.Part imageBody;
    private static RequestBody bidIdBody;
    private static RequestBody remark;
    private static API api = RetrofitInstance.getApi();

    private static Context context = ApplicationContext.getContext();


    public static void getAssignedProductList(final AssignedProductListener assignedProductListener, int id) {
        Log.d("ID", String.valueOf(id));
        Call<List<SMECommodityList>> call= api.getAssignedProductList(id);
        Log.d("Response", String.valueOf(call));
        call.enqueue(new Callback<List<SMECommodityList>>() {
            @Override
            public void onResponse(Call<List<SMECommodityList>> call, Response<List<SMECommodityList>> response) {
                if (response.code() == StaticDataManager.OK) {
                    assignedProductListener.getAssignedProductList(response.body(), "");
                } else {
//                    throw new RuntimeException(new Throwable(""+response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<SMECommodityList>> call, Throwable t) {
                Log.d("onFailure",t.toString());
                assignedProductListener.getAssignedProductList(null, context.getString(R.string.no_internet));
            }
        });
    }


    public static void getSaleBids(final SaleBidsListener listener, int page, int userId) {

        api.getSaleBids(page,userId).enqueue(new Callback<CommodityPostList>() {
            @Override
            public void onResponse(Call<CommodityPostList> call, Response<CommodityPostList> response) {
                if (response.code() == StaticDataManager.OK) {
                    listener.getSaleBids(response.body().getData(), 1, "");
                } else {
//                    throw new RuntimeException(new Throwable(""+response.code()));
                }
            }

            @Override
            public void onFailure(Call<CommodityPostList> call, Throwable t) {

                listener.getSaleBids(null, 1, context.getString(R.string.no_internet));
            }
        });


    }

    public static void getSaleBidsTemp(final SaleBidsListener listener, int page, int userId) {

        api.getSaleBidsTemp(page, userId)
                .enqueue(new Callback<List<CommodityPostList.Data>>() {
            @Override
            public void onResponse(Call<List<CommodityPostList.Data>> call, Response<List<CommodityPostList.Data>> response) {
                if (response.code() == StaticDataManager.OK) {
                    listener.getSaleBids(response.body(), 1, "");
                } else {
//                    throw new RuntimeException(new Throwable(""+response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<CommodityPostList.Data>> call, Throwable t) {

                listener.getSaleBids(null, 1, context.getString(R.string.no_internet));
            }
        });


    }

    public static void getProfileInformation(final ProfileInformationListener profileInformationListener, final int userId) {

        Log.d("User ", String.valueOf(userId));

        Call<ProfileSME> call=api.getProfileInfo(userId);

        call.enqueue(new Callback<ProfileSME>() {
            @Override
            public void onResponse(Call<ProfileSME> call, Response<ProfileSME> response) {
                if (response.isSuccessful()) {
                    Log.d("Response",response.body().toString());

                    profileInformationListener.getProfileInformation(response.body(), "");
                } else {
                    Log.d("Response", String.valueOf(response.code()));
                }

            }

            @Override
            public void onFailure(Call<ProfileSME> call, Throwable t) {
                Log.d("error" , ""+call);
                Log.d("error@" , ""+t);
            }
        });

//        api.getProfileInfo(userId)
//                .enqueue(new Callback<ProfileSME>() {
//            @Override
//            public void onResponse(Call<ProfileSME> call, Response<ProfileSME> response) {
//
//                Log.d("Response", String.valueOf(response));
//                if (response.code() == StaticDataManager.OK) {
//                    profileInformationListener.getProfileInformation(response.body(), "");
////                    Log.d("Response",response.body().toString());
//                } else {
//                    throw new RuntimeException(new Throwable(""+response.code()));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ProfileSME> call, Throwable t) {
//                profileInformationListener.getProfileInformation(null, context.getString(R.string.no_internet));
//            }
//        });


    }

    public static void postBid(final PostBidListener listener, final Bid bid) {
        api.postBid(bid)
                .enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.code() == StaticDataManager.OK) {

                    listener.getPostBidInformation(response.body(), response.message());
                    Log.d("Success",response.body().toString());
                }
                else{
                    Log.d("ResponseCode",response.message());
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                if (t instanceof IOException) {
                    context.getString(R.string.no_internet);
                }
                else{
                    Log.d("Failure",t.getMessage());
                }
            }
        });


    }

    public static void postModifiedBid(final PostModifiedBidListener listener,final Bid bid ){

        api.postModifiedBid(bid)
                .enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.code() == StaticDataManager.OK) {

                   listener.modifiedBidInformation(response.body(),"");
                }
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.d("d", "d");
            }
        });

    }

    public static void getSoldProduct(final SoldProductListener listener, int page, int userId) {
        api.getSoldProduct(page, userId)
                .enqueue(new Callback<List<SellHistoryDetail>>() {
            @Override
            public void onResponse(Call<List<SellHistoryDetail>> call, Response<List<SellHistoryDetail>> response) {
                if (response.code() == StaticDataManager.OK) {

                    listener.getSoldProduct(response.body(), 1, "");
                }
            }

            @Override
            public void onFailure(Call<List<SellHistoryDetail>> call, Throwable t) {

                listener.getSoldProduct(null, 1, context.getString(R.string.no_internet));
            }
        });


    }

    public static void getPurchasedProduct(final PurchasedProductListener listener, int page, int userId) {
        api.getPuschasedProduct(page, userId)
                .enqueue(new Callback<List<SellHistoryDetail>>() {
            @Override
            public void onResponse(Call<List<SellHistoryDetail>> call, Response<List<SellHistoryDetail>> response) {
                if (response.code() == StaticDataManager.OK) {
                    listener.getPurchasedProduct(response.body(), 1, "");
                    Log.d("Success",response.body().toString());

                }
            }

            @Override
            public void onFailure(Call<List<SellHistoryDetail>> call, Throwable t) {

                listener.getPurchasedProduct(null, 1, context.getString(R.string.no_internet));
                Log.d("######################",t.getMessage());

            }
        });


    }

//    public static void fetchLoginInformation (final Context context, final LoginInformation loginInformationListener, UserLogin request) {
//
//
//        API.login(request).enqueue(new Callback<Authenticate>() {
//            @Override
//            public void onResponse(Call<Authenticate> call, Response<Authenticate> response) {
//                if(response.code()== StaticDataManager.OK){
//                    loginInformationListener.getLoginInformation(response.body(),"");
//                }else if(response.code()==StaticDataManager.UNAUTHORISED){
//                    loginInformationListener.getLoginInformation(null,context.getString(R.string.not_authorised));
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<Authenticate> call, Throwable t) {
//                if(t instanceof IOException){
//                    loginInformationListener.getLoginInformation(null,context.getString(R.string.no_internet));
//                }
//            }
//        });
//
//
//    }


    public static void getBidInformation(final BidInformationListener listener,int bidId){

        api.getBidInformation(bidId).enqueue(new Callback<Bid>() {
            @Override
            public void onResponse(Call<Bid> call, Response<Bid> response) {
                if (response.code() == StaticDataManager.OK) {

                    listener.getBidInformation(response.body(),"");

                }
            }

            @Override
            public void onFailure(Call<Bid> call, Throwable t) {
                Log.d("d", "d");
            }
        });
    }

    public static void fetchLoginInformation(final Context context, final LoginInformation loginInformationListener, UserLogin request) {
        if (isNetworkConnected(context)) {
            api.login(request).enqueue(new Callback<Authenticate>() {
                @Override
                public void onResponse(Call<Authenticate> call, Response<Authenticate> response) {
                    if (response.code() == StaticDataManager.OK) {
                        loginInformationListener.getLoginInformation(response.body(), "");
                    } else if (response.code() == StaticDataManager.UNAUTHORISED) {
                        loginInformationListener.getLoginInformation(null, context.getString(R.string.not_authorised));
                    } else {
                        loginInformationListener.getLoginInformation(null, context.getString(R.string.server_error));
                    }

                }

                @Override
                public void onFailure(Call<Authenticate> call, Throwable t) {
                    if (t instanceof IOException) {
                        loginInformationListener.getLoginInformation(null, context.getString(R.string.server_error));
                    }
                }
            });
        } else {
            loginInformationListener.getLoginInformation(null, context.getString(R.string.no_internet));
        }

    }


    public static void getHomeProduct(final HomeProductListener homeProductListener, int page) {

        api.getHomeProduct(page)
                .enqueue(new Callback<CommodityPostList>() {
            @Override
            public void onResponse(Call<CommodityPostList> call, Response<CommodityPostList> response) {

                if (response.code() == StaticDataManager.OK) {

                    homeProductListener.getHomeProductInformation(response.body().getData(), response.body().getPageConfig().getEndPage(), "");
                }

            }

            @Override
            public void onFailure(Call<CommodityPostList> call, Throwable t) {
                if (t instanceof IOException) {
                    homeProductListener.getHomeProductInformation(null, 0, context.getString(R.string.no_internet));
                }
            }
        });


    }


    public static void getHomeProductDetails(final HomeProductDetailsListener listener, int productId, int userId) {

        //        Call<CommodityPostDetail> call = API.getHomeProductDetails("Bearer " + token,productId, userId);

        api.getHomeProductDetails(productId, userId).enqueue(new Callback<CommodityPostDetail>() {
            @Override
            public void onResponse(Call<CommodityPostDetail> call, Response<CommodityPostDetail> response) {

                if (response.code() == StaticDataManager.OK) {

                 listener.getHomeProductDetailsListener(response.body(),"");

                }
            }

            @Override
            public void onFailure(Call<CommodityPostDetail> call, Throwable t) {


//
            }
        });


    }



    public static void uploadMoneyReceipt(Context context, final UploadReceipt uploadReceptResponseListener, int bidId,String remarks, Uri uri) {

        ApiDataManager.context = context;
        Log.d("Uri",uri.toString());
        createRequestBody(bidId,remarks,uri);
        api.uploadMoneyReciept(bidIdBody,remark,imageBody).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                uploadReceptResponseListener.getUploadReceipt(response);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    private static void createRequestBody(int bidId, String remarks,Uri uri) {

        Log.d("bidid", String.valueOf(bidId));
        Log.d("Uri", String.valueOf(uri));
        Log.d("Remark", remarks);

        bidIdBody = createPartFromString(bidId );
        imageBody = prepareFilePart("reciept", uri);
        remark=createPartFromString(remarks);


    }

    private static RequestBody createPartFromString(String remarks) {
        return RequestBody.create(
                okhttp3.MultipartBody.FORM,remarks);
    }
    @NonNull
    private static RequestBody createPartFromString(int descriptionString) {
        return RequestBody.create(
                okhttp3.MultipartBody.FORM, String.valueOf(descriptionString));
    }

    @NonNull
    private static MultipartBody.Part prepareFilePart(String partName, Uri fileUri) {
        File file = new File(fileUri.getPath());
//        File file = new File(String.valueOf(fileUri));

        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse("image/jpeg"),
                        file
                );

        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }


    public static void getPurchaseBids(final PurchaseBidsListener listener, int page, int userId) {
        api.getPurchaseBids(page, userId)
                .enqueue(new Callback<List<BiddedCommodityPostList>>() {
            @Override
            public void onResponse(Call<List<BiddedCommodityPostList>> call, Response<List<BiddedCommodityPostList>> response) {
                if (response.code() == StaticDataManager.OK) {

                    listener.getPurchaseBids(response.body(), 1, "");
                }
            }

            @Override
            public void onFailure(Call<List<BiddedCommodityPostList>> call, Throwable t) {

                listener.getPurchaseBids(null, 1, context.getString(R.string.no_internet));

                Log.d("error" , ""+t);
                Log.d("error@" , ""+call);
            }
        });


    }

    public static void getWithdrawBidStatus (final WithdrawBidStatusListener listener, int bidId){

        api.withdrawBid(bidId).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.code() == StaticDataManager.OK) {

                   listener.getWithdrawBidStatus(response.body(),"");

                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });

    }


    public interface UploadReceipt {

        void getUploadReceipt(Response<ResponseBody> response);
    }


    public interface LoginInformation {
        void getLoginInformation(Authenticate response, String errorMessage);
    }

    public static void getSMEList(final SMEListListenter listener, int userId, int page){
        api.getSMEList(userId,page)
                .enqueue(new Callback<SMEList>() {
                    @Override
                    public void onResponse(Call<SMEList> call, Response<SMEList> response) {
                        if(response.code()==StaticDataManager.OK){
                            listener.getSMEList(response.body().getSme(),1,"");
                        }
                    }

                    @Override
                    public void onFailure(Call<SMEList> call, Throwable t) {

                    }
                });
    }

    public static void getTradeList(final TradeListListener listener, int agentId, int page)
    {
        api.getTradeList(agentId,page)
                .enqueue(new Callback<TradeList>() {
                    @Override
                    public void onResponse(Call<TradeList> call, Response<TradeList> response) {
                        if(response.code()==StaticDataManager.OK)
                        {
                            listener.getTradeList(response.body().getData(),1,"");
                            Log.d("Response",response.body().getData().toString());
                        }
                        else {
                            throw new RuntimeException(new Throwable(""+response.code()));
                        }
                    }

                    @Override
                    public void onFailure(Call<TradeList> call, Throwable t) {

                    }
                });
    }

    public static void getFileList(final ProductGradeListener listener,int page)
    {
        api.List().enqueue(new Callback<List<PdffileList>>() {
            @Override
            public void onResponse(Call<List<PdffileList>> call, Response<List<PdffileList>> response) {

                if(response.code()==StaticDataManager.OK)
                {
                    listener.getFileList(response.body(),1,"");
//                    Log.d("Response",response.body().getData().toString());
                }
                else {
                    throw new RuntimeException(new Throwable(""+response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<PdffileList>> call, Throwable t) {

            }
        });
    }

    public static void getDeviceList(final DeviceTokenListener listener, final int userId)
    {
        api.getDeviceList(userId).enqueue(new Callback<List<DeviceList>>() {
            @Override
            public void onResponse(Call<List<DeviceList>> call, Response<List<DeviceList>> response) {
                if(response.code()==StaticDataManager.OK)
                {
                    listener.getDeviceList(response.body(),response.message());
                    Log.d("Response",response.body().get(0).getToken());
                }
            }

            @Override
            public void onFailure(Call<List<DeviceList>> call, Throwable t) {

            }
        });
    }

    public static void getReceiveablList(final ReceiveableListListener listener, int agentId, int page)
    {
        api.getReceiveablList(agentId,page)
                .enqueue(new Callback<ReceiveableList>() {
                    @Override
                    public void onResponse(Call<ReceiveableList> call, Response<ReceiveableList> response) {
                        if(response.code()==StaticDataManager.OK)
                        {
                            listener.getReceiveablList(response.body().getData(),1,"");
                            Log.d("Response",response.body().getData().toString());
                        }
                        else {
                            throw new RuntimeException(new Throwable(""+response.code()));
                        }
                    }

                    @Override
                    public void onFailure(Call<ReceiveableList> call, Throwable t) {

                    }
                });
    }
    public static void getDeliverablelList(final DeliverableListListener listener, int agentId, int page)
    {
        api.getDeliverablelList(agentId,page)
                .enqueue(new Callback<DeliverableList>() {
                    @Override
                    public void onResponse(Call<DeliverableList> call, Response<DeliverableList> response) {
                        if(response.code()==StaticDataManager.OK)
                        {
                            listener.getDeliverablelList(response.body().getData(),1,"");
                            Log.d("Response",response.body().getData().toString());
                        }
                        else {
                            throw new RuntimeException(new Throwable(""+response.code()));
                        }
                    }

                    @Override
                    public void onFailure(Call<DeliverableList> call, Throwable t) {

                    }
                });
    }
    public static void getAgentSellerList(final AgentSellerListListener listener, int agentId, int page)
    {
        api.getAgentSellerList(agentId,page)
                .enqueue(new Callback<AgentSellerList>() {
                    @Override
                    public void onResponse(Call<AgentSellerList> call, Response<AgentSellerList> response) {
                        if(response.code()==StaticDataManager.OK)
                        {
                            listener.getAgentSellerList(response.body().getData(), Collections.singletonList(response.body()),1,"");
                            Log.d("Response",response.body().getData().toString());
                        }
                        else {
                            throw new RuntimeException(new Throwable("Error Code"+response.code()));
                        }
                    }

                    @Override
                    public void onFailure(Call<AgentSellerList> call, Throwable t) {
                        Log.d("Failure",t.getMessage());
                    }
                });
    }

    public static void getAgentBuyerList(final AgentBuyerListListener listener, int agentId, int page)
    {
       api.getAgentBuyerList(agentId,page)
              .enqueue(new Callback<AgentBuyerList>() {
                  @Override
                  public void onResponse(Call<AgentBuyerList> call, Response<AgentBuyerList> response) {
                      listener.getAgentBuyerList(response.body().getData(), Collections.singletonList(response.body()),1,"");
                  }

                  @Override
                  public void onFailure(Call<AgentBuyerList> call, Throwable t) {
                      Log.d("Failure",t.getMessage());
                  }
              });
    }

    public static void getAgentComissionList(final AgentComissionListListener listener, int agentId)
    {
        api.getAgentComissionList(agentId)
                .enqueue(new Callback<List<AgentComission>>() {
                    @Override
                    public void onResponse(Call<List<AgentComission>> call, Response<List<AgentComission>> response) {
                        if(response.code()==StaticDataManager.OK)
                        {
                            listener.getAgentComissionList(response.body(),1,"");
                        }
                        else {
                            throw new RuntimeException(new Throwable("Error Code"+response.code()));
                        }
                    }

                    @Override
                    public void onFailure(Call<List<AgentComission>> call, Throwable t) {
                        Log.d("Failure",t.getMessage());
                    }
                });
    }

    public static void getPreorderList(final PreorderListener listener, int page)
    {
        api.getPreorderList(page)
                .enqueue(new Callback<PreorderList>() {
                    @Override
                    public void onResponse(Call<PreorderList> call, Response<PreorderList> response) {
                        if(response.code()==StaticDataManager.OK)
                        {
                            listener.getPreorderList(response.body().getData(),1,"");
                        }
                        else {
                            throw new RuntimeException(new Throwable("Error Code"+response.code()));
                        }
                    }

                    @Override
                    public void onFailure(Call<PreorderList> call, Throwable t) {
                        Log.d("Failure",t.getMessage());
                    }
                });
    }

    public static void getPreorderPostDetail(final PreOrderProductDetailsListener listener, int productId, int userId) {

        //        Call<CommodityPostDetail> call = API.getHomeProductDetails("Bearer " + token,productId, userId);

        api.getPreorderPostDetail(productId, userId).enqueue(new Callback<CommodityPostDetail>() {
            @Override
            public void onResponse(Call<CommodityPostDetail> call, Response<CommodityPostDetail> response) {

                if (response.code() == StaticDataManager.OK) {

                    listener.getPreorderPostDetail(response.body(),"");
                    Log.d("Response",response.body().getDescription());

                }
            }

            @Override
            public void onFailure(Call<CommodityPostDetail> call, Throwable t) {

                Log.d("####PreorderPostDetail",t.getMessage());
//
            }
        });


    }
//    public static void PreorderPostBid(final PreorderPostBidListener listener, final Bid bid) {
//        api.PreorderPostBid(bid)
//                .enqueue(new Callback<Boolean>() {
//                    @Override
//                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
//                        if (response.code() == StaticDataManager.OK) {
//                            listener.getPreorderPostBidInformation(response.body(), "");
//                            Log.d("Success",response.body().toString());
//                        }
//                        else{
//                            Log.d("else########postBid", String.valueOf(response.code()));
//                            Log.d("else########postBid", String.valueOf(response.message()));
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Boolean> call, Throwable t) {
//                        Log.d("############postBid",t.getMessage());
//                    }
//                });
//    }
    public static void PostModifiedPreOrderBid(final PostModifiedPreOrderbidListener listener, Bid bid ){

        api.PostModifiedPreOrderBid(bid)
                .enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if (response.code() == StaticDataManager.OK) {

                            listener.getPostModifiedPreOrderbidInfo(response.body(),"");
                        }
                    }
                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        Log.d("Failure", t.getMessage());
                    }
                });

    }

    public static void withdrawPreOrderBidStatus (final WithdrawBidStatusListener listener, int bidId){

        api.withdrawPreOrderBid(bidId).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.code() == StaticDataManager.OK) {

                    listener.getWithdrawBidStatus(response.body(),"");

                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.d("#######WithdrawPreorder", t.getLocalizedMessage());
            }
        });

    }

    public static void getPreOrderOrderList (final PreOrderOrderListListener listener, int bidId){

       api.getOrderList(bidId).enqueue(new Callback<List<PreorderOrderList>>() {
           @Override
           public void onResponse(Call<List<PreorderOrderList>> call, Response<List<PreorderOrderList>> response) {
               listener.getPreOrderOrderList(response.body(),"");
           }

           @Override
           public void onFailure(Call<List<PreorderOrderList>> call, Throwable t) {
               Log.d("#######OrderList", t.getLocalizedMessage());
           }
       });
    }

//    public static void getPreorderPosts( final PreorderPostListener listener ,final PreOrder preorderPost)
//    {
//        api.postPreorder(preorderPost).enqueue(new Callback<Boolean>() {
//            @Override
//            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
//                    listener.getPreorderPosts(response.isSuccessful(),response.message());
//                    Log.d("Response", String.valueOf(response.code()));
//                    Log.d("ResponseMassw",response.message());
//
//            }
//
//            @Override
//            public void onFailure(Call<Boolean> call, Throwable t) {
//                Log.d("Fail",t.getMessage());
//            }
//        });
//    }

}


