package im.ycz.doumovie.ui.presenter;

import im.ycz.doumovie.ui.view.View;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by tinyao on 15-8-31.
 */
public abstract class Presenter<T extends View> {

    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    protected T view;

    public void setView(T view) {
        this.view = view;
    }

    public abstract void initialize();

    public abstract void resume();

    public abstract void pause();

    public void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    public void destroy() {
        if (compositeSubscription != null) {
            compositeSubscription.unsubscribe();
            compositeSubscription.clear();
            compositeSubscription = null;
        }
    }

}
