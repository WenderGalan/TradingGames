package tradinggames.com.tradinggames.tasks

import android.content.Context
import android.os.AsyncTask
import com.google.gson.Gson
import okhttp3.*
import tradinggames.com.tradinggames.models.Post
import tradinggames.com.tradinggames.util.Constantes

/**
 * Created by João on 14/12/2017.
 */

class GetAnunciosTask : AsyncTask<String, Unit, List<Post>> {

    private val getAnunciosContext: Context
    private val JSON = MediaType.parse("application/json; charset=utf-8")
    private val client: OkHttpClient = OkHttpClient()
    private val gson: Gson = Gson()
    private var failed: Boolean = true

    constructor(context: Context) {
        getAnunciosContext = context
    }

    override fun doInBackground(vararg params: String?): List<Post> {

        var searchParam: String? = params[0]


        val url: HttpUrl = HttpUrl.Builder().scheme("https")
                .host(Constantes.API_URL)
                .addPathSegment("post/search")
                .addQueryParameter("query", searchParam)
                .build()

        val request: Request = Request.Builder().url(url).get().build()

        val response: Response = client.newCall(request).execute()

        val responseBody = response.body()!!.string()

        var posts: Array<Post> = gson.fromJson(responseBody, Array<Post>::class.java)

        var arrayListPosts: ArrayList<Post> = ArrayList<Post>()

        arrayListPosts.addAll(posts)

        return arrayListPosts
    }

}

