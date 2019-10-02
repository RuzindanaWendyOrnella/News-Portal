import Dao.Sql2oDepartmentDao;
import Dao.Sql2oUserDao;
import Dao.UserDao;
import com.google.gson.Gson;
import models.Department;
import models.GeneralNews;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        Sql2oDepartmentDao departmentDao;
        Sql2oUserDao userDao;
        Connection conn;
        Gson gson = new Gson();

        staticFileLocation("/public");
        String connectionString = "jdbc:postgresql://localhost:5432/news_portal";   //connect to jadle, not jadle_test! try not to copy paste
        Sql2o sql2o = new Sql2o(connectionString, "wecode", "de los cobos");

        departmentDao = new Sql2oDepartmentDao(sql2o);
        userDao = new Sql2oUserDao(sql2o);
        conn = sql2o.open();
        get("/come", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            /*List<Department> contain =Sql2oDepartmentDao.getAll();
            model.put(" contain",  contain);*/
            return new ModelAndView(model, "index.hbs");

        }, new HandlebarsTemplateEngine());

        get("/posts2", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            /*List<Department> contain =Sql2oDepartmentDao.getAll();
            model.put(" contain",  contain);*/
            return new ModelAndView(model, "newpost-form2.hbs");
        }, new HandlebarsTemplateEngine());

        post("/posts2", (request, response) -> { //URL to make new post on POST route
            Map<String, Object> model = new HashMap<>();
            String  depart_name = request.queryParams("depart_name");
            String description = request.queryParams("description");

            /*int departmentid=Integer.parseInt(request.queryParams("departmentid"));*/
          /* int numberOfEmployees = request.queryParams("numberOfEmployees");*/
            Department department=new Department(depart_name,description);
            department.save();
            model.put("department",department);

            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/here2", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Department> contain =Sql2oDepartmentDao.getAll();
            model.put("contain",contain);
            return new ModelAndView(model, "form2.hbs");
        }, new HandlebarsTemplateEngine());
        get("/posts", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Department> contain =Sql2oDepartmentDao.getAll();

            model.put("contain",  contain);
            return new ModelAndView(model, "newpost-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/posts", (request, response) -> { //URL to make new post on POST route
            Map<String, Object> model = new HashMap<>();
            String name =request.queryParams("name");
            String positionincompany= request.queryParams("positionincompany");
            String role = request.queryParams("role");
            String departmentid=request.queryParams("departmentid");
           System.out.println(departmentid);
            User newPost = new User(name,positionincompany,role,departmentid);
            model.put("post", newPost);
            newPost.save();
            return new ModelAndView(model, "success2.hbs");
        }, new HandlebarsTemplateEngine());

        get("/here", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<User> danger = Sql2oUserDao.getAll();
           /* List<User> delete = Sql2oUserDao.clearAll();*/
            model.put("danger", danger);
            /*model.put("delete", delete);*/
            return new ModelAndView(model, "form.hbs");
        }, new HandlebarsTemplateEngine());
        get("/general", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            /*List<User> danger = Sql2oUserDao.getAll();*/
           /* model.put("danger",danger);*/
            /*List<Department> contain =Sql2oDepartmentDao.getAll();
            model.put(" contain",  contain);*/
            return new ModelAndView(model, "newpost-form3.hbs");

        }, new HandlebarsTemplateEngine());


        post("/general", (request, response) -> { //URL to make new post on POST route
            Map<String, Object> model = new HashMap<>();
            String  content = request.queryParams("content");
            String about = request.queryParams("about");
            String author = request.queryParams("author");
            /*int departmentid=Integer.parseInt(request.queryParams("departmentid"));*/
            /* int numberOfEmployees = request.queryParams("numberOfEmployees");*/
            GeneralNews general=new GeneralNews(content,about,author);
            general.save();
            model.put("general",general);

            return new ModelAndView(model, "general.hbs");
        }, new HandlebarsTemplateEngine());
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<GeneralNews> gen =GeneralNews.all();
            model.put("gen",  gen);
            return new ModelAndView(model, "form3.hbs");
        }, new HandlebarsTemplateEngine());
       get("/other", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<GeneralNews> tic =GeneralNews.all();
            model.put("tic",  tic);
            return new ModelAndView(model, "form4.hbs");
        }, new HandlebarsTemplateEngine());
        //get: delete all tasks

    }
}
