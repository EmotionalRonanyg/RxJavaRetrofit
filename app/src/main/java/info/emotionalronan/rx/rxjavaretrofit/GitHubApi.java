package info.emotionalronan.rx.rxjavaretrofit;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rx on 2017/8/31.
 */

public class GitHubApi {

    @SerializedName("emails_url")
    private String emails_url;

    @SerializedName("user_url")
    private String user_url;

    public String getEmails_url() {
        return emails_url;
    }

    public void setEmails_url(String emails_url) {
        this.emails_url = emails_url;
    }


    public String getUser_url() {
        return user_url;
    }

    public void setUser_url(String user_url) {
        this.user_url = user_url;
    }

    @Override
    public String toString() {
        return "GitHubApi{" +
                "emails_url='" + emails_url + '\'' +
                ", user_url='" + user_url + '\'' +
                '}';
    }
}
