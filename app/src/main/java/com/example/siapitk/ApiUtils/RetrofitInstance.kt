
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        private var retrofit: Retrofit? = null
        private const val BASE_URL = "https://94e2a824.ngrok.io/api/v1/mobile/"

        fun getRetrofitInstance(): Retrofit {
            retrofit = retrofit ?: retrofit2.Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
            return retrofit!!
        }
    }


}
