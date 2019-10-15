package com.lpastyle.elimtmdbapi;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersonData implements Parcelable
{

    @SerializedName("popularity")
    @Expose
    private double popularity;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("profile_path")
    @Expose
    private String profilePath;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("adult")
    @Expose
    private boolean adult;
    public final static Parcelable.Creator<PersonData> CREATOR = new Creator<PersonData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PersonData createFromParcel(Parcel in) {
            return new PersonData(in);
        }

        public PersonData[] newArray(int size) {
            return (new PersonData[size]);
        }

    }
            ;

    protected PersonData(Parcel in) {
        this.popularity = ((double) in.readValue((double.class.getClassLoader())));
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.profilePath = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.adult = ((boolean) in.readValue((boolean.class.getClassLoader())));
    }

    public PersonData() {
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(popularity);
        dest.writeValue(id);
        dest.writeValue(profilePath);
        dest.writeValue(name);
        dest.writeValue(adult);
    }

    public int describeContents() {
        return 0;
    }

}