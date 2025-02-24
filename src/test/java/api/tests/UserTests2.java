package api.tests;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.useRelaxedHTTPSValidation;

public class UserTests2 {
    // Faker is used For creating the fake/demo data
    Faker faker;
    User userPayload;
    public Logger logger;

    @BeforeClass
    public void setup()
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

        // logs
        logger = LogManager.getLogger(this.getClass());  // we can write logs using 'logger' object
    }

    @Test(priority = 1)
    public void testPostUser() {
        logger.info("********** creating user *****************");
        useRelaxedHTTPSValidation();

        Response response = UserEndPoints2.createUser(userPayload);
        response.then().log().all();

        // Check for the expected status code (201 for created)
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("********** user is created *****************");

        // Additional assertions can be added here to validate the response body
    }

    // Uncomment and implement this method when needed

    @Test(priority = 2)
    public void testGetUserByName() {
        logger.info("********** reading user info *****************");
        useRelaxedHTTPSValidation();
        Response response = UserEndPoints2.readUser(this.userPayload.getUserName());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("********** user info is displayed *****************");
    }


    @Test(priority = 3)
    public void testUpdateUserByName()
    {
        logger.info("********** update user *****************");
        //update data using payload
        // we are not updating the username because we are doing operations using 'username' here
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());


        Response response = UserEndPoints2.updateUser(this.userPayload.getUserName(),userPayload);
        // this.userPayload.getUserName() : existing username
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("********** user updated *****************");

        //checking data after update
        Response responseAfterUpdate = UserEndPoints2.readUser(this.userPayload.getUserName());
        Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);


    }

    @Test(priority = 4)
    public void testDeleteUserByName()
    {
        logger.info("********** deleting user *****************");
        Response response = UserEndPoints2.deleteUser(this.userPayload.getUserName());
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("********** deleted user  *****************");

    }
}