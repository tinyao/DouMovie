package im.ycz.doumovie.api.model;

/**
 * Created by tinyao on 15-9-4.
 */
public class ComingMoviesResult extends MovieListResult{

    private int count;

    private int start;

    private int total;

    public int getCount() {
        return count;
    }

    public int getStart() {
        return start;
    }

    public int getTotal() {
        return total;
    }

}
