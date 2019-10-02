package models;

import org.sql2o.Connection;

import java.util.Objects;

public class User {
    private String name;
    private String positionincompany;
    private int departmentId;
    private String role;
    private int id;
    private String departmentid;

    public User(String name,String positionincompany,String role ,String departmentid){
        this.name=name;
        this.positionincompany=positionincompany;
        this.role=role;
        this.departmentid=departmentid;
    }
    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDepartmentid() {
        return departmentid;
    }

    public String getPositionincompany() {
        return positionincompany;
    }

    public void setPositionincompany(String positionincompany) {
        this.positionincompany = positionincompany;
    }

    public int getDepartmentId(){
            return departmentId;
        }
    public int getId(){
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
       User user = (User) o;
        return id == user.id &&
                Objects.equals(name, user.name) &&
                Objects.equals(positionincompany, user.positionincompany) &&
                Objects.equals(role, user.role) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, positionincompany,role,id);
    }


    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO userincompany (name,positionincompany,role,departmentid) VALUES (:name,:positionincompany,:role,:departmentid);";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("positionincompany", this.positionincompany)
                    .addParameter("role", this.role)
                    .addParameter("departmentid", this.departmentid)
                    .executeUpdate()
                    .getKey();
        }
    }
}
