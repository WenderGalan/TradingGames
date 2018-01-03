package tradinggames.com.tradinggames.tasks

import android.content.Context
import android.os.AsyncTask
import com.google.gson.Gson
import okhttp3.*
import tradinggames.com.tradinggames.models.FileModel
import tradinggames.com.tradinggames.util.Constantes

/**
 * Created by João on 14/12/2017.
 */

class UploadImagesTask : AsyncTask<FileModel, Unit, String>{

    val uploadImagesContext: Context
    val gson: Gson = Gson()
    val client: OkHttpClient = OkHttpClient()
    private val JSON = MediaType.parse("application/json; charset=utf-8")

    constructor(context: Context){
        uploadImagesContext = context
    }

    override fun doInBackground(vararg info: FileModel): String {

        var file: FileModel = info[0]

//        val url: HttpUrl = HttpUrl.Builder().host(Constantes.API_URL)
//                .addPathSegment("post/upload")
//                .addQueryParameter("postId", file.postId)
//                .build()

        val request: Request = Request.Builder().url(Constantes.Anuncios.UPLOAD_FILE)
                .post(RequestBody.create(JSON, gson.toJson(file.files)))
                .build()

        val response: Response = client.newCall(request).execute()

        val responseBody = response.body()!!.string()

        return responseBody
    }
}