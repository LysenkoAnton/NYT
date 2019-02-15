package com.example.myapplicationtablet;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RequestsApi {

    //https://api.nytimes.com/svc/mostpopular/v2/emailed/30.json?api-key=
    @GET("/svc/mostpopular/v2/{typeArticle}/30.json")
    public Call<ResApi> getMostEmailedArticle(@Path("typeArticle") String typeArticle, @Query("api-key") String api_key);

      /*  @GET("/posts")
        public Call<List<Post>> getAllPosts();

        @GET("/posts")
        public Call<List<Post>> getPostOfUser(@Query("userId") int id);

        @POST("/posts")
        public Call<Post> postData(@Body Post data);
*/
}
