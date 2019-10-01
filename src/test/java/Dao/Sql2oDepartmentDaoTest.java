/*
package Dao;

import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oDepartmentDaoTest {
    private static Connection conn; //these variables are now static.
    private static Sql2oDepartmentDao departmentDao; //these variables are now static.
    private static Sql2oUserDao userDao; //these variables are now static.


    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/jaddle_test";  //connect to postgres test database
        Sql2o sql2o = new Sql2o(connectionString, "wecode", "de los cobos"); //changed user and pass to null
        departmentDao = new Sql2oDepartmentDao(sql2o);
        userDao = new Sql2oUserDao(sql2o);
        conn = sql2o.open();        //open connection once before this test file is run
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("clearing database");
        departmentDao.clearAll(); //clear all restaurants after every test
        userDao.clearAll(); //clear all restaurants after every test
    }
    @AfterClass     //changed to @AfterClass (run once after all tests in this file completed)
    public static void shutDown() throws Exception{ //changed to static
        conn.close(); // close connection once after this entire test file is finished
        System.out.println("connection closed");
    }
    @Test
    public void add() {
    }

    @Test
    public void addDepartmentForUser() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void getAllUsersForDepartment() {
    }

    @Test
    public void update() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void clearAll() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void getAllDepartmentByUser() {
    }
}*/
