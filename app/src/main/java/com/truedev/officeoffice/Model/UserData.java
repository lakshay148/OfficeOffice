package com.truedev.officeoffice.Model;

import java.io.Serializable;


public class UserData implements Serializable {

    public String name ="";
    public String roleName="";
    public boolean chekBoolen=false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChekBoolen() {
        return chekBoolen;
    }

    public void setChekBoolen(boolean chekBoolen) {
        this.chekBoolen = chekBoolen;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}