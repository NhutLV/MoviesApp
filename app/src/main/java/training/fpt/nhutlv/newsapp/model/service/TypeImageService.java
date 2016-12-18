package training.fpt.nhutlv.newsapp.model.service;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import training.fpt.nhutlv.newsapp.model.response.APITypeImage;

/**
 * Created by NhutDu on 19/12/2016.
 */

public interface TypeImageService {

    @GET("configuration")
    Call<APITypeImage> getImageURL(@Query("api_key") String api_key);
}
