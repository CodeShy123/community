package life.majiang.community.provioder;

import com.alibaba.fastjson.JSON;
import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithuProvider {
    public String getAcessToken(AccessTokenDTO accessTokenDTO){
     MediaType MediaType_JSON = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(MediaType_JSON,JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            System.out.println(string);
            return string;
        } catch (IOException e) {

        }
        return null;
    }
    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try {
            Response  response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);//转换GithubUser为对象
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
