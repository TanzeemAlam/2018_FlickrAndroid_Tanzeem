package com.example.tanzeem.flickrapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static java.lang.System.load;

public class MainActivity extends AppCompatActivity {

    List<PhotoGetterSetter> listPhoto;
    private com.android.volley.RequestQueue mQueue;
    EditText inputText;
    Button button;
    ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9;
    private static final String API_KEY = "1dd900f9300147d4adf33fbc19cdd49e";
    private static final String SECRET_KEY = "6247e988ad3a7e1f";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.inputtext);
        button = findViewById(R.id.button);
        imageView1 = findViewById(R.id.image1);
        imageView2 = findViewById(R.id.image2);
        imageView3 = findViewById(R.id.image3);
        imageView4 = findViewById(R.id.image4);
        imageView5 = findViewById(R.id.image5);
        imageView6 = findViewById(R.id.image6);
        imageView7 = findViewById(R.id.image7);
        imageView8 = findViewById(R.id.image8);
        imageView9 = findViewById(R.id.image9);



        mQueue = Volley.newRequestQueue(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse(inputText);
            }
        });

       /* RecyclerView recyclerView = findViewById(R.id.photo_recyclerview);
        PhotoRecyclerView adapter = new PhotoRecyclerView(this,listPhoto);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
        */

    }

    private void jsonParse(EditText tag) {

        String url ="https://api.flickr.com/services/rest/?method=flickr.photos.search&per_page=10&nojsoncallback=1&format=json&tags="+
                tag+"&api_key="+API_KEY;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try{
                            JSONArray jsonArray = response.getJSONArray("photos");
                            //JSONArray jsonArray1 = jsonArray[4];

                            for(int i = 0;i<jsonArray.length();i++){

                                int count = 1;
                                JSONObject photos = jsonArray.getJSONObject(i);

                                int farm = photos.getInt("farm");
                                int server = photos.getInt("server");
                                int id = photos.getInt("id");
                                String secret = photos.getString("secret");

                                String url = "http://farm" + farm + ".staticflickr.com/" +
                                        server + "/" + id + "_" + secret + ".jpg";
                                if(count == 1)
                                    loadImageFromUrl1(url);
                                else if(count == 2)
                                    loadImageFromUrl2(url);
                                else if(count == 3)
                                    loadImageFromUrl3(url);
                                else if(count == 4)
                                    loadImageFromUrl4(url);
                                else if(count == 5)
                                    loadImageFromUrl5(url);
                                else if(count == 6)
                                    loadImageFromUrl6(url);
                                else if(count == 7)
                                    loadImageFromUrl7(url);
                                else if(count == 8)
                                    loadImageFromUrl8(url);
                                else if(count == 9)
                                    loadImageFromUrl9(url);

                                count += 1;
                                //listPhoto.add(new PhotoGetterSetter("http://farm" + farm + ".staticflickr.com/" +
                                //        server + "/" + id + "_" + secret + ".jpg"));
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }

    private void loadImageFromUrl1(String url) throws IOException {
        URL urle = new URL(url);
        Bitmap bmp = BitmapFactory.decodeStream(urle.openConnection().getInputStream());
        imageView1.setImageBitmap(bmp);
    }

    private void loadImageFromUrl2(String url)throws IOException {
        URL urle = new URL(url);
        Bitmap bmp = BitmapFactory.decodeStream(urle.openConnection().getInputStream());
        imageView2.setImageBitmap(bmp);
    }
    private void loadImageFromUrl3(String url) throws IOException{
        URL urle = new URL(url);
        Bitmap bmp = BitmapFactory.decodeStream(urle.openConnection().getInputStream());
        imageView3.setImageBitmap(bmp);
    }
    private void loadImageFromUrl4(String url) throws IOException{
        URL urle = new URL(url);
        Bitmap bmp = BitmapFactory.decodeStream(urle.openConnection().getInputStream());
        imageView4.setImageBitmap(bmp);
    }
    private void loadImageFromUrl5(String url) throws IOException{
        URL urle = new URL(url);
        Bitmap bmp = BitmapFactory.decodeStream(urle.openConnection().getInputStream());
        imageView5.setImageBitmap(bmp);
    }
    private void loadImageFromUrl6(String url)throws IOException {
        URL urle = new URL(url);
        Bitmap bmp = BitmapFactory.decodeStream(urle.openConnection().getInputStream());
        imageView6.setImageBitmap(bmp);
    }
    private void loadImageFromUrl7(String url) throws IOException{
        URL urle = new URL(url);
        Bitmap bmp = BitmapFactory.decodeStream(urle.openConnection().getInputStream());
        imageView7.setImageBitmap(bmp);
    }
    private void loadImageFromUrl8(String url)throws IOException {
        URL urle = new URL(url);
        Bitmap bmp = BitmapFactory.decodeStream(urle.openConnection().getInputStream());
        imageView8.setImageBitmap(bmp);
    }
    private void loadImageFromUrl9(String url)throws IOException {
        URL urle = new URL(url);
        Bitmap bmp = BitmapFactory.decodeStream(urle.openConnection().getInputStream());
        imageView9.setImageBitmap(bmp);
    }

}
