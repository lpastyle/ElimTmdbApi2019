package com.lpastyle.elimtmdbapi;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static String LOG_TAG = MainActivity.class.getSimpleName();

    ITmdbApi tmdbApi = null;
    int mCurrentPage = 1;
    List<PersonData> results = new ArrayList<>();
    PersonListAdapter mPersonListAdapter = null;

    // views
    Context mContext = this;
    Button mNextBt;
    Button mPrevBt;
    RecyclerView mPopularPersonRv;
    TextView mPageNumberTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init API client
        if (ApiClient.get() != null) tmdbApi = ApiClient.get().create(ITmdbApi.class);

        // set button listeners
        mNextBt = findViewById(R.id.next_page_bt);
        mPrevBt = findViewById(R.id.prev_page_bt);
        mNextBt.setOnClickListener(this);
        mPrevBt.setOnClickListener(this);

        // Current page number text view
        mPageNumberTv = findViewById(R.id.page_number_tv);
        setPageNumber();


        // set recycler view
        mPopularPersonRv = findViewById(R.id.popular_person_rv);
        mPopularPersonRv.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this); // use a linear layout manager
        mPopularPersonRv.setLayoutManager(layoutManager);

        mPersonListAdapter = new PersonListAdapter(this, results); // attach a custom adapter
        mPopularPersonRv.setAdapter(mPersonListAdapter);

        // call TMDB api
        refreshPersons();

    }

    private void refreshPersons() {
        if (tmdbApi != null) {
            Call<PersonPopularResponse> call = tmdbApi.getPersonPopular(ITmdbApi.KEY, String.valueOf(mCurrentPage));
            call.enqueue(new Callback<PersonPopularResponse>() {
                @Override
                public void onResponse(Call<PersonPopularResponse> call, Response<PersonPopularResponse> response) {
                    results.clear();
                    switch (response.code()) {
                        case 200:
                            PersonPopularResponse personResponse = response.body();
                            if (personResponse != null && personResponse.getResults() != null) {
                                results.addAll(personResponse.getResults());
                                Log.d(LOG_TAG, "Number of popular person found=" + results.size());
                            }
                            break;
                        default:
                            Log.e(LOG_TAG, "HTTP error " + response.code());
                            results.clear();
                            mCurrentPage = 0;
                            Toast toast = Toast.makeText(mContext, R.string.toast_http_error, Toast.LENGTH_LONG);
                            toast.show();
                            break;

                    }
                    mPersonListAdapter.notifyDataSetChanged();
                    mPopularPersonRv.scrollToPosition(0);
                    setPageNumber();
                }

                @Override
                public void onFailure(Call<PersonPopularResponse> call, Throwable t) {
                    Log.e(LOG_TAG, "Call to 'getPersonPopular' failed");
                    results.clear();
                    mCurrentPage = 0;
                    mPersonListAdapter.notifyDataSetChanged();
                    setPageNumber();
                    Toast toast = Toast.makeText(mContext, R.string.toast_network_error, Toast.LENGTH_LONG);
                    toast.show();

                }
            });

        } else {
            Log.e(LOG_TAG, "Api not initialized");
        }
    }

    // helper to update page number caption
    private void setPageNumber() {
        String caption = getResources().getString(R.string.page_number, mCurrentPage);
        mPageNumberTv.setText(caption);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.prev_page_bt:
                if (mCurrentPage > 1) {
                    mCurrentPage--;
                    refreshPersons();
                } else {
                    Toast toast = Toast.makeText(this, R.string.toast_no_previous_page, Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
            case R.id.next_page_bt:
                mCurrentPage++;
                refreshPersons();
            default:
                Log.w(LOG_TAG, "unhandled click event");
        }
    }
}
