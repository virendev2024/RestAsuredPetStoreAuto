package api.tests;

import api.endpoints.UserEndPoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.useRelaxedHTTPSValidation;

public class UserTests {
    // Faker is used For creating the fake/demo data
    Faker faker;
    User userPayload;

    @BeforeClass
    public void setupData()
    {
        // Initialize Faker
        faker = new Faker();
        userPayload = new User();

        // Set user data using Faker
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setUserName(faker.name().username());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5,10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());
    }

    @Test(priority = 1)
    public void testPostUser() {
        useRelaxedHTTPSValidation();

        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();

        // Check for the expected status code (201 for created)
        Assert.assertEquals(response.getStatusCode(), 200);

        // Additional assertions can be added here to validate the response body
    }

    // Uncomment and implement this method when needed

    @Test(priority = 2)
    public void testGetUserByName() {
        useRelaxedHTTPSValidation();
        Response response = UserEndPoints.readUser(this.userPayload.getUserName());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }


    @Test(priority = 3)
    public void testUpdateUserByName()
    {
        //update data using payload
        // we are not updating the username because we are doing operations using 'username' here
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());


        Response response = UserEndPoints.updateUser(this.userPayload.getUserName(),userPayload);
        // this.userPayload.getUserName() : existing username
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

        //checking data after update
        Response responseAfterUpdate = UserEndPoints.readUser(this.userPayload.getUserName());
        Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);


    }

    @Test(priority = 4)
    public void testDeleteUserByName()
    {

        Response response = UserEndPoints.deleteUser(this.userPayload.getUserName());
        Assert.assertEquals(response.getStatusCode(),200);

    }
}