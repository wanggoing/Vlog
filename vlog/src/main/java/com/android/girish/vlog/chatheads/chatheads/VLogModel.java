package com.android.girish.vlog.chatheads.chatheads;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;

public class VLogModel implements Parcelable {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({UNKNOWN, VERBOSE, DEBUG, INFO, WARN, ERROR, ASSERT})
    public @interface LogPriority {}

    /**
     * Priority constant for the println method.
     */
    // TODO: @girish do we need unknown? can we set verbose by default?
    public static final int UNKNOWN = -1;
    /**
     * Priority constant for the println method; use Log.v.
     */
    public static final int VERBOSE = 2;

    /**
     * Priority constant for the println method; use Log.d.
     */
    public static final int DEBUG = 3;

    /**
     * Priority constant for the println method; use Log.i.
     */
    public static final int INFO = 4;

    /**
     * Priority constant for the println method; use Log.w.
     */
    public static final int WARN = 5;

    /**
     * Priority constant for the println method; use Log.e.
     */
    public static final int ERROR = 6;

    /**
     * Priority constant for the println method.
     */
    public static final int ASSERT = 7;

    private int mLogPriority = UNKNOWN;
    private String mTag;
    private String mLogMessage;


    public VLogModel(@LogPriority int priority, String tag, String logMessage) {
        mLogPriority = priority;
        mTag = tag;
        mLogMessage = logMessage;
    }

    protected VLogModel(Parcel in) {
        mLogPriority = in.readInt();
        mTag = in.readString();
        mLogMessage = in.readString();
    }

    public int getLogPriority() {
        return mLogPriority;
    }

    public void setLogPriority(int mLogPriority) {
        this.mLogPriority = mLogPriority;
    }

    @NonNull
    public String getTag() {
        return mTag;
    }

    public void setTag(String mTag) {
        this.mTag = mTag;
    }

    @NonNull
    public String getLogMessage() {
        return mLogMessage;
    }

    public void setLogMessage(String mLogMessage) {
        this.mLogMessage = mLogMessage;
    }

    public static final Creator<VLogModel> CREATOR = new Creator<VLogModel>() {
        @Override
        public VLogModel createFromParcel(Parcel in) {
            return new VLogModel(in);
        }

        @Override
        public VLogModel[] newArray(int size) {
            return new VLogModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mLogPriority);
        dest.writeString(mTag);
        dest.writeString(mLogMessage);
    }
}
