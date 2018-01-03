package tradinggames.com.tradinggames.tasks

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import okhttp3.*
import tradinggames.com.tradinggames.activities.MainAnuncios
import tradinggames.com.tradinggames.models.Post
import tradinggames.com.tradinggames.util.Constantes

/**
 * Created by João on 14/12/2017.
 */

class CreateAnuncioTask : AsyncTask<Post, Unit, Post> {

    private val createAnuncioContex: Context
    private val JSON = MediaType.parse("application/json; charset=utf-8")
    private val okHttp: OkHttpClient = OkHttpClient()
    private val gson: Gson = Gson()
    private var failed: Boolean = true
    private lateinit var anuncio: Post

    constructor(createAnuncio: Context) {
        createAnuncioContex = createAnuncio
    }

    override fun onPostExecute(result: Post) {
        if (failed) {
            Toast.makeText(createAnuncioContex, "Algum erro ocorreu no cadastro do anuncio !", Toast.LENGTH_LONG)
        } else {
            createAnuncioContex.startActivity(Intent(createAnuncioContex, MainAnuncios::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    .putExtra("postId", result.postId))
            Log.d("background", "depois do intent")
        }
    }

    override fun doInBackground(vararg info: Post): Post {
        anuncio = info[0]


        val request: Request = Request.Builder().url(Constantes.Anuncios.REGISTER_URL)
                .post(RequestBody.create(JSON, gson.toJson(anuncio)))
                .build()

        val response: Response = okHttp.newCall(request).execute()

        val responseBody = response.body()!!.string()

        if (!response.isSuccessful || responseBody == "") {
            failed = true
            return Post()
        } else {
            failed = false
            return gson.fromJson(responseBody, Post::class.java)
        }

    }

}