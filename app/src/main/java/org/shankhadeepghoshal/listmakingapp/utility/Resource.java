package org.shankhadeepghoshal.listmakingapp.utility;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Resource<T> {
    @NonNull public final Status status;
    @Nullable public final T data;
    @Nullable public final String message;

    private Resource(Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(Status.SUCCESS, data, null);
    }

    public static <T> Resource<T> error(@NonNull T data) {
        return new Resource<>(Status.ERROR, data, null);
    }

    public static Resource error() {
        return new Resource<>(Status.ERROR, null, null);
    }

    public static <T> Resource<T> loading(@NonNull T data) {
        return new Resource<>(Status.LOADING, data, null);
    }

    // Hopefully this is compiled down to some kind of integer #fingerscrossed
    public enum Status { SUCCESS, ERROR, LOADING }
}