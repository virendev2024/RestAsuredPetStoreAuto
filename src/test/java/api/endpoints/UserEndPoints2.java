package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static api.endpoints.Routes.post_url;
import static io.restassured.RestAssured.given;

//created for CRUD operation
public class UserEndPoints2 {

    //Test methods are available only in test cases not in endpoints

    //method that will load properties file (resource.bundle)
    static ResourceBundle getURL()  // method created for getting URl's from properties file
    {
        ResourceBundle routes = ResourceBundle.getBundle("routes"); // properties file name
        return routes;
    }

    public static Response createUser(User payload)     // user payload is not developed we will develop that
    {
        String post_url = getURL().getString("post_url");    //reading from properties file
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)

                .when()
                    .post(post_url);   // accesing using classname and variable name without creating any object
         return response;
    }

    public static Response readUser(String userName)      // will read using username
    {
        String get_url = getURL().getString("get_url");
        Response response = given()
//                    .accept(ContentType.JSON)
                    .pathParam("username",userName)
                .when()
                    .get(Routes.get_url);
        return response;
    }

    public static Response deleteUser(String userName)      // will read using username
    {
        String delete_url = getURL().getString("delete_url");
        Response response = given()
                    .accept(ContentType.JSON)
                    .pathParam("username",userName)
                .when()
                    .delete(Routes.delete_url);
        return response;
    }

    public static Response updateUser(String userName , User payload)
    {
        String update_url = getURL().getString("update_url");
        Response response =given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .pathParam("username",userName)
                    .body(payload)
                .when()
                    .put(Routes.update_url);
        return response;
    }


}
