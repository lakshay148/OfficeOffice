package com.truedev.officeoffice.Model;

import java.io.Serializable;

/**
 * Created by prashantchauhan on 19/5/16.
 */

public class UserData implements Serializable {
    public String name ="";
    public String roleName="";
    public boolean isSelected;
    public String emp_name = "null";
    public String emp_id = "null";
    public String emp_password = "null";
    public String emp_role = "null";
    public String module_role = "role";
    public String module_domain = "domain";
    public String module_empID = "empId";

    public UserData(){

    }
    public UserData(String name) {
        this.name = name;
    }
    public UserData(String name, boolean isSelected) {
        this.name = name;
        this.isSelected = isSelected;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_password() {
        return emp_password;
    }

    public void setEmp_password(String emp_password) {
        this.emp_password = emp_password;
    }

    public String getEmp_role() {
        return emp_role;
    }

    public void setEmp_role(String emp_role) {
        this.emp_role = emp_role;
    }

    public String getModule_domain() {
        return module_domain;
    }

    public void setModule_domain(String module_domain) {
        this.module_domain = module_domain;
    }

    public String getModule_role() {
        return module_role;
    }

    public void setModule_role(String module_role) {
        this.module_role = module_role;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getModule_empID() {
        return module_empID;
    }

    public void setModule_empID(String module_empID) {
        this.module_empID = module_empID;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleName() { return roleName;}

    public void setRoleName(String roleName) { this.roleName = roleName;}

    public boolean isSelected() {
        return isSelected;
    }
    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
