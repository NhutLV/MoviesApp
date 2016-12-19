package training.fpt.nhutlv.newsapp.model.service;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import training.fpt.nhutlv.newsapp.entities.Movies;
import training.fpt.nhutlv.newsapp.entities.TypeImage;
import training.fpt.nhutlv.newsapp.model.Configuration;
import training.fpt.nhutlv.newsapp.model.response.APIMovies;
import training.fpt.nhutlv.newsapp.model.response.APITypeImage;
import training.fpt.nhutlv.newsapp.utils.Constants;


/**
 * Created by NhutDu on 19/12/2016.
 */

public class MoviesServiceImpl {
    private static final String TAG = MoviesServiceImpl.class.getSimpleName();

    //region Properties

    private Context mContext;

    //endregion

    //region Constructor

    public MoviesServiceImpl(Context mContext) {
        this.mContext = mContext;
    }

    public MoviesServiceImpl() {
    }

    //endregion

    public void getPopularMovies(training.fpt.nhutlv.newsapp.utils.Callback<ArrayList<Movies>> movies){
        MoviesService service = Configuration.getClient().create(MoviesService.class);
        Call<APIMovies> call = service.getPopularMovies(Constants.API_KEY);
        call.enqueue(new Callback<APIMovies>() {
            @Override
            public void onResponse(Call<APIMovies> call, Response<APIMovies> response) {
                APIMovies apiMovies = response.body();
                Log.d(TAG, "onResponse: "+response.isSuccessful());
                if(response.isSuccessful() && apiMovies.getListMovies()!=null){
                    Log.d(TAG, "onResponse: "+apiMovies.getListMovies().get(0).getTitle());
                }else{
                    Log.d(TAG, "onResponse: Failed load popular movies");
                }
            }

            @Override
            public void onFailure(Call<APIMovies> call, Throwable t) {
                Log.d(TAG, "onFailure: ",t);
            }
        });
    }

}
