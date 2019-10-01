import Dao.Sql2oDepartmentDao;
import Dao.Sql2oUserDao;
import Dao.UserDao;
import com.google.gson.Gson;
import models.Department;
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
        get("/", (req, res) -> {
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
            /*int departmentid=Integer.parseInt(request.params("departmentid"));*/
            User newPost = new User(name,positionincompany,role);
            model.put("post", newPost);
            newPost.save();
            return new ModelAndView(model, "success2.hbs");
        }, new HandlebarsTemplateEngine());

        get("/here", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<User> danger = Sql2oUserDao.getAll();
            System.out.println(danger);
            model.put("danger", danger);
            return new ModelAndView(model, "form.hbs");
        }, new HandlebarsTemplateEngine());
      /*  //CREATE
        post("/departments/:departmentId/user/:userId", "application/json", (req, res) -> {

            int restaurantId = Integer.parseInt(req.params("DepartmentId"));
            int  userId = Integer.parseInt(req.params(" UserInCompanyId"));
            Department department = departmentDao.findById(restaurantId);
            User user = userDao.findById(userId);


            if (department != null && user != null) {
                //both exist and can be associated
                userDao.addUserToDepartment(user, department);
                res.status(201);
                return gson.toJson(String.format("Restaurant '%s' and Foodtype '%s' have been associated", user.getName(), department.getName()));
            } else {
                throw new ApiException(404, String.format("Restaurant or Foodtype does not exist"));
            }
        });

        get("/departments/:id/users", "application/json", (req, res) -> {
            int DepartmentId = Integer.parseInt(req.params("id"));
           Department departmentToFind = departmentDao.findById(DepartmentId);
            if (departmentToFind == null) {
                throw new ApiException(404, String.format("No restaurant with the id: \"%s\" exists", req.params("id")));
            } else if (departmentDao.getAllUsersForDepartment(DepartmentId).size() == 0) {
                return "{\"message\":\"I'm sorry, but no foodtypes are listed for this restaurant.\"}";
            } else {
                return gson.toJson(departmentDao.getAllUsersForDepartment(DepartmentId));
            }
        });
        get("/users/:id/departments", "application/json", (req, res) -> {
            int userId = Integer.parseInt(req.params("id"));
            User userToFind = userDao.findById(userId);
            if (userToFind == null){
                throw new ApiException(404, String.format("No foodtype with the id: \"%s\" exists", req.params("id")));
            }
            else if (userDao.getAllDepartmentsForUsers(userId).size()==0){
                return "{\"message\":\"I'm sorry, but no restaurants are listed for this foodtype.\"}";
            }
            else {
                return gson.toJson(userDao.getAllDepartmentsForUsers(userId));
            }
        });


        *//*post("/departments/:departmentId/reviews/new", "application/json", (req, res) -> {
            int restaurantId = Integer.parseInt(req.params("restaurantId"));
            Review review = gson.fromJson(req.body(), Review.class);
            review.setCreatedat(); //I am new!
            review.setFormattedCreatedAt();
            review.setRestaurantId(restaurantId); //we need to set this separately because it comes from our route, not our JSON input.
            reviewDao.add(review);
            res.status(201);
            return gson.toJson(review);
        });*//*

        post("/users/new", "application/json", (req, res) -> {
            User user = gson.fromJson(req.body(), User.class);
            userDao.add(user);
            res.status(201);
            return gson.toJson(user);
        });

        //READ
        get("/department", "application/json", (req, res) -> {
            System.out.println(departmentDao.getAll());

            if(departmentDao.getAll().size() > 0){
                return gson.toJson(departmentDao.getAll());
            }

            else {
                return "{\"message\":\"I'm sorry, but no restaurants are currently listed in the database.\"}";
            }

        });

        get("/departments/:id", "application/json", (req, res) -> { //accept a request in format JSON from an app
            int DepartmentId = Integer.parseInt(req.params("id"));
           Department departmentToFind = departmentDao.findById(DepartmentId);
            if (departmentToFind == null){
                try {
                    throw new ApiException(404, String.format("No restaurant with the id: \"%s\" exists", req.params("id")));
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
            return gson.toJson(departmentToFind);
        });

       *//* get("/restaurants/:id/reviews", "application/json", (req, res) -> {
            int restaurantId = Integer.parseInt(req.params("id"));

         Department departmentToFind = departmentDao.findById(restaurantId);
            List<Review> allReviews;

            if (departmentToFind == null){
                try {
                    throw new ApiException(404, String.format("No restaurant with the id: \"%s\" exists", req.params("id")));
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }

            allReviews = reviewDao.getAllReviewsByRestaurant(restaurantId);

            return gson.toJson(allReviews);
        });

        get("/restaurants/:id/sortedReviews", "application/json", (req, res) -> { //// TODO: 1/18/18 generalize this route so that it can be used to return either sorted reviews or unsorted ones.
            int restaurantId = Integer.parseInt(req.params("id"));
            Restaurant restaurantToFind = restaurantDao.findById(restaurantId);
            List<Review> allReviews;
            if (restaurantToFind == null) {
                try {
                    throw new ApiException(404, String.format("No restaurant with the id: \"%s\" exists", req.params("id")));
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
            allReviews = reviewDao.getAllReviewsByRestaurantSortedNewestToOldest(restaurantId);
            return gson.toJson(allReviews);
        });
*//*
        get("/users", "application/json", (req, res) -> {
            return gson.toJson(userDao.getAll());
        });


        //CREATE
        post("/departments/new", "application/json", (req, res) -> {
            Department department = gson.fromJson(req.body(), Department.class);
            departmentDao.add(department);
            res.status(201);
            return gson.toJson(department);
        });

        //FILTERS
        exception(ApiException.class, (exception, req, res) -> {
            ApiException err = exception;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json");
            res.status(err.getStatusCode());
            res.body(gson.toJson(jsonMap));
        });


        after((req, res) ->{
            res.type("application/json");
        });

*/
    }
}
