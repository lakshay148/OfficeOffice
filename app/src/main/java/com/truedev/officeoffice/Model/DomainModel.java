package com.truedev.officeoffice.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by dipanshugarg on 2/6/16.
 */
public class DomainModel implements Serializable {

    @SerializedName("name")
    public String mDomain;

    @SerializedName("created_by")
    public String mCreatedBy;


    @SerializedName("id")
    public int mId;


    @SerializedName("created_at")
    public String mCreatedAt;

    public String getmDomain() {
        return mDomain;
    }

    public void setmDomain(String mDomain) {
        this.mDomain = mDomain;
    }

    public String getmCreatedBy() {
        return mCreatedBy;
    }

    public void setmCreatedBy(String mCreatedBy) {
        this.mCreatedBy = mCreatedBy;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmCreatedAt() {
        return mCreatedAt;
    }

    public void setmCreatedAt(String mCreatedAt) {
        this.mCreatedAt = mCreatedAt;
    }

    @Override
    public String toString() {
        return "DomainModel{" +
                "mDomain='" + mDomain + '\'' +
                ", mCreatedBy='" + mCreatedBy + '\'' +
                ", mId=" + mId +
                ", mCreatedAt='" + mCreatedAt + '\'' +
                '}';
    }


}
