package org.shankhadeepghoshal.listmakingapp.utility;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class UserIdInjectingInterceptor implements Interceptor {
    private final String userId;

    @Inject
    public UserIdInjectingInterceptor(String userId) {
        this.userId = userId;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request originalRequest = chain.request();

        return userId != null ?
                chain.proceed(originalRequest.newBuilder()
                .header("X-User-Id", this.userId)
                .method(originalRequest.method(), originalRequest.body())
                .build()) :
                chain.proceed(originalRequest);
    }
}