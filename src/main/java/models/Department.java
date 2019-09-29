package models;

public class Department {
    private String name;
    private String description;
    private int numberOfEmployees;
    private int id;

    public Department(String name,String description,int numberOfEmployees){
        this.name=name;
        this.description=description;
        this.numberOfEmployees=numberOfEmployees;
    }
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public int getNumberOfEmployees(){
        return numberOfEmployees;
    }
    public int getId(){
        return id;
    }
}
