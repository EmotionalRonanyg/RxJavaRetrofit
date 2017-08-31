package info.emotionalronan.rx.rxjavaretrofit;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by rx on 2017/8/31.
 */

public interface MovieService {
    @GET("top250")
    Observable<HttpResult<List<Subject>>> getTopMovie(
            @Query("start") int start,
            @Query("count") int count
    );
}
