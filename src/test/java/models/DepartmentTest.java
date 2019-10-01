package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DepartmentTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getName() {
        Department testDepartment = setupDepartment();
        assertEquals("Human Resources", testDepartment.getName());
    }

    @Test
    public void setName() {
        Department testDepartment= setupDepartment();
        testDepartment.setName("job");
        assertNotEquals("Human Resources",  testDepartment.getName());
    }

    public Department setupDepartment() {
        return new Department("Human Resources","looking for a secretary",30);
    }

    @Test
    public void getDescription() {
        Department testDepartment=setupDepartment();
        assertEquals("looking for a secretary",testDepartment.getDescription());
    }
    @Test
    public void setDescription() {
        Department testDepartment= setupDepartment();
        testDepartment.setDescription("job search");
        assertNotEquals("looking for a secretary",  testDepartment.getDescription());
    }

    @Test
    public void getNumberOfEmployees() {
        Department testDepartment=setupDepartment();
        assertEquals(30,testDepartment.getNumberOfEmployees());
    }
    @Test
    public void setNumberOfEmployees() {
        Department testDepartment= setupDepartment();
        testDepartment.setNumberOfEmployees(30);
        assertNotEquals(38,  testDepartment.getNumberOfEmployees());
    }
 /*   @Test
    public void getId() {
        Department testDepartment=setupDepartment();
        assertEquals(2,testDepartment.getId());
    }*/
    @Test
    public void setId() {
        Department testDepartment= setupDepartment();
        testDepartment.setId(2);
        assertNotEquals(7,  testDepartment.getId());
    }
}