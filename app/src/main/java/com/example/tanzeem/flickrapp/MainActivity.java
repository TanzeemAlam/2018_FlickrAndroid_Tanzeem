package com.example.tanzeem.flickrapp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.load;

public class MainActivity extends AppCompatActivity {

    //API and SECRET Key
    private static final String API_KEY = "1dd900f9300147d4adf33fbc19cdd49e";
    private static final String SECRET_KEY = "6247e988ad3a7e1f";

    //Log tag
    private static final String TAG = MainActivity.class.getSimpleName();

    private ProgressDialog progressDialog;
    String url = "";

    private List modelList = new ArrayList();
    ListView listView;
    private CustomAdapter adapter;
    EditText inputtext;
    Button button;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //input text and button for performing the search operation.
        inputtext = findViewById(R.id.inputtext);
        button = findViewById(R.id.button);

        listView = findViewById(R.id.listview);
        adapter = new CustomAdapter(this,modelList);
        listView.setAdapter(adapter);

        progressDialog = new ProgressDialog(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Showing progress dialog box before making a request
                progressDialog.setMessage("Loading.....");
                progressDialog.show();

                //Calling the json parsing method and calling with the input text
                JsonParsing(inputtext);
            }
        });
    }

    //Method for Json Parsing
    public void JsonParsing(EditText inputtext){

        url = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key="+API_KEY +
                "&format=json&nojsoncallback=1&text=+"+inputtext+"&extras=url_o";


        //Creating volley request object
        JsonArrayRequest photoReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();

                        //Parsing Json
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject photos = response.getJSONObject(i);

                                //Creating object of object
                                JSONObject photo = photos.getJSONObject("photo");

                                int farm = photo.getInt("farm");
                                int server = photo.getInt("server");
                                int id = photo.getInt("id");
                                String secret = photo.getString("secret");

                                //Creating the actual image url from the data feeded from above.
                                String imageurl = "https://farm" + farm + ".staticflickr.com/" + server + "/" + id + "_" + secret + ".jpg";
                                Model model = new Model();
                                model.setThumbnailUrl(photo.getString(imageurl));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        // notifying list adapter about data changes so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG,"Error: "+ error.getMessage());
                hidePDialog();
            }
        });

        //Adding request to request queue
        AppController.getInstance().addToRequestQueue(photoReq);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
