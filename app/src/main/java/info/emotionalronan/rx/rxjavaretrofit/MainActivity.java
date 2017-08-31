package info.emotionalronan.rx.rxjavaretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

   private static final String BASE_URL = "http://104.224.181.198:3000/";

   private Button clickMeBtn;
   private TextView resultTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clickMeBtn = findViewById(R.id.click_me_btn);
        resultTxt = findViewById(R.id.result_txt);

        clickMeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMovie();
            }
        });

    }

    //进行网络请求
    private void getMovie() {

        String baseUrl  = "https://api.douban.com/v2/movie/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieService movieService = retrofit.create(MovieService.class);

        Call<MovieEntity> call = movieService.getTopMovie(0,10);

        call.enqueue(new Callback<MovieEntity>() {
            @Override
            public void onResponse(Call<MovieEntity> call, Response<MovieEntity> response) {
                resultTxt.setText(response.body().toString());
                Log.e("TSAG","===---onResponse==="+response.body().toString());

            }

            @Override
            public void onFailure(Call<MovieEntity> call, Throwable t) {

                resultTxt.setText(t.getMessage());
                Log.e("TSAG","===--onFailure-==="+ t.getMessage());

            }
        });

    }
}
