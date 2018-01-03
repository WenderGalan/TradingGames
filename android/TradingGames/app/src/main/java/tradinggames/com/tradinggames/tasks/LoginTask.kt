package tradinggames.com.tradinggames.tasks

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import okhttp3.*
import tradinggames.com.tradinggames.models.User
import tradinggames.com.tradinggames.util.Constantes

/**
 * Created by João on 10/12/2017.
 */

class LoginTask : AsyncTask<String, Unit, User> {

    private val client: OkHttpClient = OkHttpClient()
    private val gson: Gson = Gson()
    private val JSON = MediaType.parse("application/json; charset=utf-8")
    private val loginContext: Context
    private lateinit var user: User

    private var failed: Boolean = true

    constructor(context: Context) {
        loginContext = context
    }

    override fun onPreExecute() {
    }

    override fun onPostExecute(result: User) {

        if (failed) {
            Log.d("postexecute", "Não passou no login!")
            Toast.makeText(loginContext, "Usuário ou senha inválido !", Toast.LENGTH_LONG)
        } else {
            Log.d("postexecute", "passou no login")
//            loginContext.startActivity(Intent(loginContext, MainAnuncios::class.java)
//                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).putExtra("userId", result.userId))
        }
    }

    override fun doInBackground(vararg info: String?): User {

        user = User()

        user.userEmail = info[0]
        user.userPassword = info[1]

        Log.d("background", "inside it")

        Log.d("background", "email: ${user.userEmail}")

        Log.d("background", "password: ${user.userPassword}")

        val request: Request = Request.Builder()
                .url(Constantes.Login.LOGIN_URL)
                .post(RequestBody.create(JSON, gson.toJson(user)))
                .build()

        val response: Response = client.newCall(request).execute()

        val responseBody = response.body()!!.string()

        Log.d("background", "response: ${responseBody}")

        if (!response.isSuccessful || responseBody == "") {
            failed = true
            Log.d("background", "não teve resposta válida")
            return User()
        } else {
            failed = false
            Log.d("background", "teve resposta")
            var userFromJson: User = gson.fromJson(responseBody, User::class.java)
            return userFromJson
        }
    }

}