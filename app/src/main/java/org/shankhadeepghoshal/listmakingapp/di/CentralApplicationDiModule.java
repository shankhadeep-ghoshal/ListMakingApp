package org.shankhadeepghoshal.listmakingapp.di;

import android.content.Context;
import android.content.SharedPreferences;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import org.shankhadeepghoshal.listmakingapp.GlobalConstants;
import org.shankhadeepghoshal.listmakingapp.LocalDatabase;
import org.shankhadeepghoshal.listmakingapp.R;
import org.shankhadeepghoshal.listmakingapp.scopes.ApplicationScope;
import org.shankhadeepghoshal.listmakingapp.utility.UserIdInjectingInterceptor;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import dagger.android.support.DaggerApplication;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Module
public class CentralApplicationDiModule {

    @ApplicationScope
    @Provides
    public Context provideContext(DaggerApplication daggerApplication) {
        return daggerApplication.getApplicationContext();
    }

    @ApplicationScope
    @Provides
    public LocalDatabase provideLocalDatabase(Context context) {
        return LocalDatabase.getInstance(context);
    }

    @ApplicationScope
    @Provides
    public RequestOptions provideRequestOptions() {
        return RequestOptions
                .placeholderOf(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
    }

    @ApplicationScope
    @Provides
    public RequestManager provideGlideInstance(Context context, RequestOptions requestOptions) {
        return Glide.with(context).setDefaultRequestOptions(requestOptions);
    }

    @ApplicationScope
    @Provides
    public Cache provideOkHttpCache(Context context) {
        return new Cache(context.getCacheDir(), 10 * 1024 * 1024);
    }

    @ApplicationScope
    @Provides
    public SharedPreferences provideSharedPreference(Context context) {
        return context.getSharedPreferences(context.getString(R.string.shared_pref_name),
                        Context.MODE_PRIVATE);
    }

    @ApplicationScope
    @Provides
    public Interceptor provideNetworkInterceptor(@Named("userId")String userId) {
        return new UserIdInjectingInterceptor(userId);
    }

    @ApplicationScope
    @Provides
    public OkHttpClient provideOkHttpClient(Cache cache) {
        return new OkHttpClient
                .Builder()
                .cache(cache)
                .build();
    }

    @ApplicationScope
    @Provides
    public Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(GlobalConstants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory
                        .createWithScheduler(Schedulers.io()))
                .build();
    }

    @Provides
    @Named("userId")
    public String getUserId(SharedPreferences preferences) {
        return preferences.getString("user_id",null);
    }
}