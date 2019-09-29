package models;

public class User {
    private String name;
    private String positionInCompany;
 /*   private int departmentId;*/
    private String role;
    private int id;

    public User(String name,String positionInCompany,String role){
        this.name=name;
        this.positionInCompany=positionInCompany;

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
    public void setRole(String role){
        this.role=role;
    }
    public String getPositionInCompany(){
        return positionInCompany;
    }
    public void setPositionInCompany(String positionInCompany) {
        this.positionInCompany = positionInCompany;
    }
  /*  public int getDepartmentId(){
        return departmentId;
    }*/
    public int getId(){
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
