package Dao;

import models.Department;
import models.User;

import java.util.List;

public interface UserDao {
    void add(User user);
    void addUserToDepartment(User user, Department department);

//    List<User> getAll();
    List<Department> getAllDepartmentsForUsers(int id);

    //update
    //omit for now

    //delete
    void deleteById(int id);
    List<User> clearAll();

    User findById(int id);
}
