package Dao;


import models.Department;
import models.User;

import java.util.List;

public interface DepartmentDao {
    void add(Department department);
    void addDepartmentForUser(Department department, User user);

    //read
   /* List<Department> getAll();*/
    List<User> getAllUsersForDepartment(int DepartmentId);

    //update
    //omit for now
    void update(int id, String name, String description, int numberOfEmployees);

    //delete
    void deleteById(int id);
    void clearAll();

  Department findById(int id);
    List<User> getAllDepartmentByUser(int DepartmentId);
}
