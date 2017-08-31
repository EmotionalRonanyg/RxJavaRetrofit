package info.emotionalronan.rx.rxjavaretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

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

        String baseUrl = "https://api.douban.com/v2/movie/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                //添加RxJava2的支持
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        MovieService movieService = retrofit.create(MovieService.class);

        //Retrofit 结合 RxJava 请求网络内容
        movieService.getTopMovie(0, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieEntity movieEntity) {

                        resultTxt.setText(movieEntity.toString());
                        Log.e(TAG, "" + movieEntity.toString());

                    }

                    @Override
                    public void onError(Throwable e) {

                        resultTxt.setText(e.getMessage());
                        Log.e(TAG, "" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                        Toast.makeText(MainActivity.this, "Get Top movie Completed", Toast.LENGTH_SHORT).show();
                    }
                });


    }
}
