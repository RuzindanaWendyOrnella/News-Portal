package models;

import org.sql2o.Connection;

import java.util.Objects;

public class Department {
    private String  depart_name;
    private String description;
    private int numberOfEmployees;
    private String depart_news;
    private int id;

    public Department(String depart_name,String description,String depart_news){
        this.depart_name=depart_name;
        this.description=description;
        this.depart_news=depart_news;
    }

    public String getDepart_name() {
        return depart_name;
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

    public String getDepart_news() {
        return depart_news;
    }

    public void setDepart_name(String depart_name) {
        this.depart_name = depart_name;
    }

    public void setDescription(String description) {
        this.description=description;
    }
    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees=numberOfEmployees;
    }
    public void setId(int id) {
        this.id=id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Department)) {
            return false;
        }
      Department department = (Department) o;
        return id == department.id &&
                Objects.equals(depart_name, department.depart_name) &&
                Objects.equals(description, department.description) &&
                Objects.equals(numberOfEmployees, department.description) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(depart_name, description,numberOfEmployees,id);
    }
    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO department (depart_name,description,depart_news) VALUES (:depart_name,:description,:depart_news);";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("depart_name", this.depart_name)
                    .addParameter("description", this.description)
                    .addParameter("depart_news", this.depart_news)
            /*        .addParameter("role", this.role)*/
                    .executeUpdate()
                    .getKey();
        }
    }
}
