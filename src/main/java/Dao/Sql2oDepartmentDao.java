package Dao;

import models.Department;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oDepartmentDao implements DepartmentDao{

    private static Sql2o sql2o;

    public Sql2oDepartmentDao(Sql2o sql2o) { this.sql2o = sql2o; }

    @Override
    public void add(Department department) {
        String sql = "INSERT INTO department  Create TABLE department(Depart_name ,numberOfEmployees ,description ) VALUES (:name, :numberOfEmployees,  :description)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(department)
                    .executeUpdate()
                    .getKey();
            department.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void addDepartmentForUser(Department department, User user) {
        String sql = "INSERT INTO department_userincompany (Departmentid, UserInCompanyid) VALUES (:DepartmentId, :UserInCompanyId)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("DepartmentId", department.getId())
                    .addParameter("UserInCompanyId", user.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }

    }


    public static List<Department> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM department")
                    .executeAndFetch(Department.class);
        }
    }

    @Override
    public List<User> getAllUsersForDepartment(int Departmentid) {
        return null;
    }

    @Override
    public void update(int id, String name, String description, int numberOfEmployees) {
        String sql = "UPDATE departments SET (name, description, numberOfEmployees) = (:name, :description, :numberOfEmployees) WHERE id=:id"; //CHECK!!!
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("description", description)
                    .addParameter("numberOfEmployees", numberOfEmployees)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from department WHERE id = :id"; //raw sql
        String deleteJoin = "DELETE from department_userincompany WHERE Departmentid = :DepartmentId";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            con.createQuery(deleteJoin)
                    .addParameter("DepartmentId", id)
                    .executeUpdate();

        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE from department";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public Department findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM department WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Department.class);
        }
    }

    @Override
    public List<User> getAllDepartmentByUser(int departmentId) {
        List<User> users = new ArrayList(); //empty list
        String joinQuery = "SELECT userincompanyid FROM department_userInCompany WHERE  Departmentid = : departmentId";

        try (Connection con = sql2o.open()) {
            List<Integer> allUserInCompanyIds = con.createQuery(joinQuery)
                    .addParameter("departmentId", departmentId)
                    .executeAndFetch(Integer.class);
            for (Integer userId : allUserInCompanyIds){
                String userQuery = "SELECT * FROM UserInCompany WHERE id = :UserInCompanyId";
               users.add(
                        con.createQuery(userQuery)
                                .addParameter("UserInCompanyId", userId)
                                .executeAndFetchFirst(User.class));
            }
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return users;
    }
    }

