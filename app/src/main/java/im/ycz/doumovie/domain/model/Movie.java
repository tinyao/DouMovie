package im.ycz.doumovie.domain.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tinyao on 15-8-31.
 */
public class Movie implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("alt")
    private String url;

    @SerializedName("images")
    private Image image;

    @SerializedName("subtype")
    private String subType;

    @SerializedName("summary")
    private String summary;

    @SerializedName("rating")
    private Rating rating;

    @SerializedName("ratings_count")
    private int ratingCount;

    @SerializedName("year")
    private String year;

    @SerializedName("casts")
    private List<Cast> casts;

    @SerializedName("directors")
    private List<Director> directors;

    private List<Photo> photos;

    private List<String> countries;

    private List<String> genres;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public Image getImage() {
        return image;
    }

    public String getSummary() {
        return summary;
    }

    public Rating getRating() {
        return rating;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public String getYear() {
        return year;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getSubType() {
        return subType;
    }

    public List<Cast> getCasts() {
        return casts;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public List<String> getGenres() {
        return genres;
    }

    public List<String> getCountries() { return countries; }

    public List<Photo> getPhotos() {
        return photos;
    }

    public String getCastsFormated() {
        if (casts == null) return "";
        StringBuilder sb = new StringBuilder();
        for (Cast cast : casts) {
            sb.append(cast.getName()).append("/");
        }
        if (sb.length() > 1) {
            return sb.substring(0, sb.length() - 1);
        } else {
            return "";
        }
    }

    public String getDirectorsFormated() {
        if (directors == null) return "";
        StringBuilder sb = new StringBuilder();
        for (Director director : directors) {
            sb.append(director.getName()).append("/");
        }
        if (sb.length() > 1) {
            return sb.substring(0, sb.length() - 1);
        } else {
            return "";
        }
    }

    public String getGenresFormated() {
        if (genres == null) return "";
        StringBuilder sb = new StringBuilder();
        for (String gene : genres) {
            sb.append(gene).append("/");
        }
        if (sb.length() > 1) {
            return sb.substring(0, sb.length() - 1);
        } else {
            return "";
        }
    }

    public String getCountriesFormated() {
        if (countries == null) return "";
        StringBuilder sb = new StringBuilder();
        for (String country : countries) {
            sb.append(country).append("/");
        }
        if (sb.length() > 1) {
            return sb.substring(0, sb.length() - 1);
        } else {
            return "";
        }
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", url='" + url + '\'' +
                ", image=" + image +
                ", subType='" + subType + '\'' +
                ", summary='" + summary + '\'' +
                ", rating=" + rating +
                ", ratingCount=" + ratingCount +
                ", year='" + year + '\'' +
                ", casts=" + casts +
                ", directors=" + directors +
                ", genres=" + genres +
                '}';
    }
}
