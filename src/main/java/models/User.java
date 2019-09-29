package models;

public class User {
    private String name;
    private String positionInCompany;
    private int departmentId;
    private String role;
    private int id;

    public User(String name,String positionInCompany,int departmentId,String role){
        this.name=name;
        this.positionInCompany=positionInCompany;
        this.departmentId=departmentId;
    }
    public String getName(){
        return name;
    }
    public String getPositionInCompany(){
        return positionInCompany;
    }
    public int getDepartmentId(){
        return departmentId;
    }
    public int getId(){
        return id;
    }
}
