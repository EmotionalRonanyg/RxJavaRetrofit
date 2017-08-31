package info.emotionalronan.rx.rxjavaretrofit;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by rx on 2017/8/31.
 */

public interface MovieService {
    @GET("top250")
    Observable<MovieEntity> getTopMovie(
            @Query("start") int start,
            @Query("count") int count
    );
}
