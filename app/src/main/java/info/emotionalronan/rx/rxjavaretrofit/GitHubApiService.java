package info.emotionalronan.rx.rxjavaretrofit;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by rx on 2017/8/31.
 */

public interface GitHubApiService {

    @GET("")

    Observable<GitHubApi> getInfo(
            @Query("user_url") String user_url,
            @Query("emails_url") String emails_url
    );
}
