package im.ycz.doumovie.bus.event;

/**
 * Created by tinyao on 15-9-8.
 */
public class NetworkErrorEvent {

    public static final int NETWORK_WRONG = 0;
    public static final int HTTP_400 = 400;
    public static final int HTTP_404 = 404;

    private int status;

    private String errorMsg;

    public NetworkErrorEvent(int status, String errorMsg) {
        this.status = status;
        this.errorMsg = errorMsg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
