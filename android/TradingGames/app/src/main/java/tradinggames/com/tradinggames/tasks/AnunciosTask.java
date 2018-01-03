package tradinggames.com.tradinggames.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import tradinggames.com.tradinggames.models.Post;
import tradinggames.com.tradinggames.util.Constantes;

/**
 * Created by toppeiradasgalaxias on 20/11/2017.
 */

public class AnunciosTask extends AsyncTask<String, Void, Post> {

    OkHttpClient client = new OkHttpClient();
    Context anunciosContext;

    public AnunciosTask(Context context) {
        this.anunciosContext = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Post response) {
        super.onPostExecute(response);

        if (response.equals(null)) {
            Toast.makeText(anunciosContext, "Não foi possível carregar os anúncios", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    @Override
    protected Post doInBackground(String... strings) {
        try {
            Gson gson = new Gson();

            Request request = new Request.Builder()
                    .url(Constantes.Anuncios.REGISTER_URL + "?query=" + strings[0] + "&category=" + strings[0])
                    .get()
                    .build();

            Response response = client.newCall(request).execute();

            String jsonResponse = response.body().toString();

            if (jsonResponse.isEmpty()) {
                return null;
            }

            return new Post(gson.fromJson(jsonResponse, Post.class));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}


