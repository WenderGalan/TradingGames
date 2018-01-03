package tradinggames.com.tradinggames.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import tradinggames.com.tradinggames.models.User;
import tradinggames.com.tradinggames.util.Constantes;

/**
 * Created by joao on 25/10/2017.
 */


public class RegisterTask extends AsyncTask<User, Void, String> {

    OkHttpClient client = new OkHttpClient();
    private Context registerContext;

    public RegisterTask(Context context){
        registerContext = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);
        if (response.equals("false")){
            Toast.makeText(registerContext, "Ocorreu um erro na hora de enviar as informações", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(registerContext, response, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected String doInBackground(User... users) {
        try {
            Gson gson = new Gson();
            String user = gson.toJson(users[0]);
            Request request = new Request.Builder()
                    .url(Constantes.Register.REGISTER_URL)
                    .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), user))
                    .build();

            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
           e.printStackTrace();
            return "false";
        }

    }

}
