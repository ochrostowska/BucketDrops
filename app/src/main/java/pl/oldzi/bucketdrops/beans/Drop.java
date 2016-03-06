package pl.oldzi.bucketdrops.beans;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Oldzi on 04.03.2016.
 */
public class Drop extends RealmObject {
    private String what;
    @PrimaryKey
    private long time;
    private long added;
    private boolean completed;

    public Drop(String what, long added, long time, boolean completed) {
        this.what = what;
        this.added = added;
        this.completed = completed;
        this.time = time;
    }

    public Drop() {
    }

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public long getAdded() {
        return added;
    }

    public void setAdded(long added) {
        this.added = added;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }




}
