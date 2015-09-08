package im.ycz.doumovie.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import im.ycz.doumovie.R;
import im.ycz.doumovie.bus.MovieBus;
import im.ycz.doumovie.bus.event.NetworkErrorEvent;

/**
 * Created by tinyao on 15-8-31.
 */
public abstract class BaseActivity extends AppCompatActivity{

    // Obtain same Singleton eventBus
    private MovieBus eventBus = MovieBus.getBus();
    private NetworkFailureHandler networkFailureHandler;

    @Override
    protected void onResume() {
        super.onResume();
        networkFailureHandler = new NetworkFailureHandler();
        eventBus.register(networkFailureHandler);
    }

    @Override
    protected void onPause() {
        super.onPause();
        eventBus.unregister(networkFailureHandler);
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private class NetworkFailureHandler {
        @Subscribe
        public void onFailure(NetworkErrorEvent event) {
            if (event.getStatus() == NetworkErrorEvent.NETWORK_WRONG) {
                BaseActivity.this.showToast(getString(R.string.error_network_wrong));
            } else {
                BaseActivity.this.showToast(getString(R.string.error_http_request_failure));
            }
        }
    }

}
