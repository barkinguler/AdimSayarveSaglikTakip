package com.example.yurumesayar.RestApi;



import com.example.yurumesayar.Models.Bilgiler;

import java.util.List;

import retrofit2.Call;

public class ManagerAll extends BaseManager{
private static ManagerAll ourInstance=new ManagerAll();

public static synchronized ManagerAll getInstance(){

    return ourInstance;

}
public Call<List<Bilgiler>> getirBilgileri(){

Call<List<Bilgiler>> call=getRestApiClient().BilgiGetir();
return call;
}

    public Call<List<Bilgiler>> getirAdim(){

        Call<List<Bilgiler>> call=getRestApiClient().BilgiGetiradim();
        return call;
    }

    public Call<List<Bilgiler>> getirAdimgecmis(){

        Call<List<Bilgiler>> call=getRestApiClient().BilgiGetiradimgecmis();
        return call;
    }

    public Call<List<Bilgiler>> getirKilogecmis(){

        Call<List<Bilgiler>> call=getRestApiClient().BilgiGetirkilogecmis();
        return call;
    }
    public Call<List<Bilgiler>> getirHata(){

        Call<List<Bilgiler>> call=getRestApiClient().BilgiGetirhata();
        return call;
    }


}
