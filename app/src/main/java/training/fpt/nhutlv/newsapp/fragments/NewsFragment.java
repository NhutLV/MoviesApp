package training.fpt.nhutlv.newsapp.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.rahatarmanahmed.cpv.CircularProgressView;

import java.util.ArrayList;

import training.fpt.nhutlv.newsapp.R;
import training.fpt.nhutlv.newsapp.adapters.RecyclerViewMoviesBoxAdapter;
import training.fpt.nhutlv.newsapp.entities.Movies;
import training.fpt.nhutlv.newsapp.lib.EndlessRecyclerViewScrollListener;
import training.fpt.nhutlv.newsapp.lib.GridSpacingItemDecoration;
import training.fpt.nhutlv.newsapp.model.service.MoviesServiceImpl;
import training.fpt.nhutlv.newsapp.utils.Callback;

/**
 * Created by NhutDu on 18/12/2016.
 */

public class NewsFragment extends Fragment {

    private static final String TAG = NewsFragment.class.getSimpleName();
    RecyclerView mRecyclerView;
    RecyclerViewMoviesBoxAdapter moviesBoxAdapter;
    ArrayList<Movies> mMovies;
    MoviesServiceImpl mMoviesService;
    CircularProgressView mProgress;
    EndlessRecyclerViewScrollListener mScrollListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMovies = new ArrayList<>();
        mMoviesService = new MoviesServiceImpl(getActivity());

    }

    public static Fragment newInstance(){
        Fragment fragment = new NewsFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies,container,false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_movies);
        mProgress = (CircularProgressView) view.findViewById(R.id.progress_view);
        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(5), true));

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        moviesBoxAdapter = new RecyclerViewMoviesBoxAdapter(getActivity().getApplicationContext(),mMovies);
        mRecyclerView.setAdapter(moviesBoxAdapter);

        mMoviesService.getPopularMovies(1,new Callback<ArrayList<Movies>>() {
            @Override
            public void onResult(ArrayList<Movies> movies) {
                mProgress.setVisibility(View.INVISIBLE);
                mMovies.addAll(movies);
                moviesBoxAdapter.notifyDataSetChanged();
            }
        });

        mScrollListener = new EndlessRecyclerViewScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                mMoviesService.getPopularMovies(2,new Callback<ArrayList<Movies>>() {
                    @Override
                    public void onResult(ArrayList<Movies> movies) {
                        mProgress.setVisibility(View.INVISIBLE);
                        mMovies.addAll(movies);
                        moviesBoxAdapter.notifyDataSetChanged();
                    }
                });
            }
        };

        mRecyclerView.addOnScrollListener(mScrollListener);

        return view;

    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
