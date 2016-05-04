package org.drools.minecraft.model;
//TODO: merge with EventOneShot, or remove entirely

public class Event {

    private String id;
    protected boolean hasFired;

    public Event(String id) {
        this.id = id;
        hasFired = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean getHasFired() {
        return hasFired;
    }

    public void setHasFired(boolean hasFired) {
        this.hasFired = hasFired;
    }

}
