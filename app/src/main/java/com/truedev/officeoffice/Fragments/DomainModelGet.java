package com.truedev.officeoffice.Fragments;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dipanshugarg on 2/6/16.
 */
public class  DomainModelGet {

    public String mDomain;
    public String mCreatedBy;
    public int mId;
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
}
