package api.endpoints;

import static io.restassured.RestAssured.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//created for CRUD operation
public class UserEndPoints {

    //Test methods are available only in test cases not in endpoints

    public static Response createUser(User payload)     // user payload is not developed we will develop that
    {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)

                .when()
                    .post(Routes.post_url);   // accesing using classname and variable name without creating any object
         return response;
    }

    public static Response readUser(String userName)      // will read using username
    {
        Response response = given()
//                    .accept(ContentType.JSON)
                    .pathParam("username",userName)
                .when()
                    .get(Routes.get_url);
        return response;
    }

    public static Response deleteUser(String userName)      // will read using username
    {
        Response response = given()
                    .accept(ContentType.JSON)
                    .pathParam("username",userName)
                .when()
                    .delete(Routes.delete_url);
        return response;
    }

    public static Response updateUser(String userName , User payload)
    {
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
