package Dao;

import models.Department;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oUserDao implements UserDao {
    private static Sql2o sql2o;


    public Sql2oUserDao(Sql2o sql2o) { this.sql2o = sql2o; }

    @Override
    public void add(User user) {
        String sql = "INSERT INTO User  create TABLE userincompany(name ,positionInCompany ) VALUES (:name, :positionInCompany)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(user)
                    .executeUpdate()
                    .getKey();
            user.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void addUserToDepartment(User user, Department department) {
        String sql = "INSERT INTO DEpartment_userincompany (Departmentid ,UserInCompanyid ) VALUES (:DepartmentId, :userInCompanyId)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("DepartmentId", department.getId())
                    .addParameter("UserInCompanyId", user.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

//    @Override
    public static List<User> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM userincompany")
                    .executeAndFetch(User.class);
        }
    }

    @Override
    public List<Department> getAllDepartmentsForUsers(int id) {
        List<Department> departments = new ArrayList();
        String joinQuery = "SELECT departmentid FROM DEpartment_userInCompany WHERE  UserInCompanyid = : UserInCompanyId";

        try (Connection con = sql2o.open()) {
            List<Integer> allRestaurantIds = con.createQuery(joinQuery)
                    .addParameter(" UserInCompanyId", id)
                    .executeAndFetch(Integer.class); //what is happening in the lines above?
            for (Integer restaurantId : allRestaurantIds){
                String restaurantQuery = "SELECT * FROM departments WHERE id = :restaurantId";
                departments.add(
                        con.createQuery(restaurantQuery)
                                .addParameter("restaurantId", restaurantId)
                                .executeAndFetchFirst(Department.class));
            } //why are we doing a second sql query - set?
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return departments;
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from userincompany WHERE id = :id"; //raw sql
        String deleteJoin = "DELETE from DEpartment_userInCompany WHERE UserInCompanyid = :UserInCompanyId";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            con.createQuery(deleteJoin)
                    .addParameter("UserInCompanyId", id)
                    .executeUpdate();

        } catch (Sql2oException ex){
            System.out.println(ex);
        }

    }

    @Override
    public void clearAll() {
        String sql = "DELETE from userincompany";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public User findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM userincompany WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(User.class);
        }
    }

}
