package tradinggames.com.tradinggames.tasks

import android.content.Context
import android.os.AsyncTask
import com.google.gson.Gson
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import tradinggames.com.tradinggames.models.FileModel
import tradinggames.com.tradinggames.util.Constantes

/**
 * Created by João on 14/12/2017.
 */

class GetImagesTask : AsyncTask<String, Unit, FileModel> {

    val getImagesContext: Context
    val gson: Gson = Gson()
    val client: OkHttpClient = OkHttpClient()

    constructor(context: Context){
        getImagesContext = context
    }

    override fun doInBackground(vararg info: String): FileModel {

        val url: HttpUrl = HttpUrl.Builder().host(Constantes.API_URL)
                .addPathSegment("post/loadFile")
                .addQueryParameter("postId", info[0])
                .build()

        val request: Request = Request.Builder().url(url).get().build()

        val response: Response = client.newCall(request).execute()

        val responseBody = response.body()!!.string()

        return gson.fromJson(responseBody, FileModel::class.java)
    }

}