package de.juliansoftware.user;

import de.juliansoftware.group.Group;


import java.util.Date;
import java.util.UUID;

public class UserData {

    private UUID uuid;
    private Group group;

    private Date date;

    private String name;
    public UserData(UUID uuid, Group group, Date date, String name) {
        this.uuid = uuid;
        this.group = group;
        this.date = date;
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }
}
