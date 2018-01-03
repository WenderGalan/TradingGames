package tradinggames.com.tradinggames.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import tradinggames.com.tradinggames.models.User;
import tradinggames.com.tradinggames.util.Constantes;

/**
 * Created by joao on 25/10/2017.
 *
 *  ESTA TASK NÃO ESTÁ MAIS SENDO UTILIZADA
 *
 */

public class OldLoginTask extends AsyncTask<String, Void, User> {

    private OkHttpClient client = new OkHttpClient();

    private static final int LOGIN = 0;
    private static final int PASSWORD = 1;

    private Context loginContext;

    public OldLoginTask(Context context) {
        loginContext = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(User response) {
       super.onPostExecute(response);

       if (response == null) {
            Toast.makeText(loginContext, "Falha ao realizar login", Toast.LENGTH_SHORT).show();
            return;
       }

        //exemplo para chamar outra tela e levar os dados juntos
        /*Intent intent = new Intent(loginContext, MainAnuncios.class);

        Bundle bundle = new Bundle();

        bundle.putString("email", response.getUserEmail());
        bundle.putString("senha", response.getUserPassword());

        intent.putExtras(bundle);
        loginContext.startActivity(intent);*/
    }

    @Override
    protected User doInBackground(String... strings) {
        try {
            Gson gson = new Gson();
            String credenciais = gson.toJson(montarCredenciais(strings));
            Request request = new Request.Builder()
                    .url(Constantes.Login.LOGIN_URL)
                    .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), credenciais))
                    .build();

            Response response = client.newCall(request).execute();
            // rever este if quando consertar a api
            String jsonResponse = response.body().string();
            if (jsonResponse.isEmpty()) {
                return null;
            }

            return new User(gson.fromJson(jsonResponse, User.class)) ;
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("response", "aqui");
            return null;
        }
    }

    private Map<String, String> montarCredenciais(String... strings) {
        Map<String, String> credencial = new HashMap<>();
        credencial.put("userEmail", strings[LOGIN]);
        credencial.put("userPassword", strings[PASSWORD]);

        return credencial;

    }
}
