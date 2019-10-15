package com.lpastyle.elimtmdbapi;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 *  Class automatically created from the following json sample
 *  with the help of http://www.jsonschema2pojo.org/
 *
 * {
 * "page":1,
 *     "total_results":17687,
 *     "total_pages":885,
 *     "results":[
 *         {
 *         "popularity":23.936,
 *         "id":17832,
 *         "profile_path":"\/72fz51l5P8HTnp1eHZCEwfBDQVa.jpg",
 *         "name":"Carla Gugino",
 *         "adult":false
 *         }
 *     ]
 * }
 */
public class PersonPopularResponse implements Parcelable
{

    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("total_results")
    @Expose
    private int totalResults;
    @SerializedName("total_pages")
    @Expose
    private int totalPages;
    @SerializedName("results")
    @Expose
    private List<PersonData> results = null;
    public final static Parcelable.Creator<PersonPopularResponse> CREATOR = new Creator<PersonPopularResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PersonPopularResponse createFromParcel(Parcel in) {
            return new PersonPopularResponse(in);
        }

        public PersonPopularResponse[] newArray(int size) {
            return (new PersonPopularResponse[size]);
        }

    }
            ;

    protected PersonPopularResponse(Parcel in) {
        this.page = ((int) in.readValue((int.class.getClassLoader())));
        this.totalResults = ((int) in.readValue((int.class.getClassLoader())));
        this.totalPages = ((int) in.readValue((int.class.getClassLoader())));
        in.readList(this.results, (PersonData.class.getClassLoader()));
    }

    public PersonPopularResponse() {
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<PersonData> getResults() {
        return results;
    }

    public void setResults(List<PersonData> results) {
        this.results = results;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(page);
        dest.writeValue(totalResults);
        dest.writeValue(totalPages);
        dest.writeList(results);
    }

    public int describeContents() {
        return 0;
    }

}