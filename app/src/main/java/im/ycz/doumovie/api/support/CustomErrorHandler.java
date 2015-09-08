package im.ycz.doumovie.api.support;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;

import im.ycz.doumovie.bus.event.NetworkErrorEvent;
import im.ycz.doumovie.domain.model.Movie;
import retrofit.ErrorHandler;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by tinyao on 15-9-8.
 */
public class CustomErrorHandler implements ErrorHandler {

    private static final Handler MAIN_LOOPER_HANDLER = new Handler(Looper.getMainLooper());
    private Bus bus;

    public CustomErrorHandler(Bus eventBus) {
        this.bus = eventBus;
    }

    @Override
    public Throwable handleError(RetrofitError cause) {
        final NetworkErrorEvent errorEvent;
        if (cause.getKind().equals(RetrofitError.Kind.NETWORK)) {
            errorEvent = new NetworkErrorEvent(NetworkErrorEvent.NETWORK_WRONG, cause.getMessage());
        } else {
            Response response = cause.getResponse();
            errorEvent = new NetworkErrorEvent(response.getStatus(), cause.getMessage());
        }
        // we need to make sure we post in the UI thread
        MAIN_LOOPER_HANDLER.post(new Runnable() {
            @Override public void run() {
                bus.post(errorEvent);
            }
        });
        return cause;
    }
}
