package api.tests;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DDTests
{
    // 1. Creating multiple users by passing the data using data providers
    // 2. and then deleting them further using username

    //creating payload using 'User' class

    // parameters should be passed in same order as excel
    @Test(priority = 1 , dataProvider = "Data" , dataProviderClass = DataProviders.class)
    public void testPostUser(String userId,String userName , String fname , String lname , String usermail, String pwd, String ph)
    {
        // pojo
        // If we have 5 users data then this step will repeat 5 times without any loop or anythin
        User userPayLoad = new User();

        userPayLoad.setId(Integer.parseInt(userId));
        userPayLoad.setEmail(usermail);
        userPayLoad.setUserName(userName);
        userPayLoad.setFirstName(fname);
        userPayLoad.setLastName(lname);
        userPayLoad.setPassword(pwd);
        userPayLoad.setPhone(ph);


         Response response =UserEndPoints.createUser(userPayLoad);
        Assert.assertEquals(response.getStatusCode(),200);

    }

    // for each iteration it is 
    @Test(priority = 2 , dataProvider = "UserNames" , dataProviderClass = DataProviders.class)
    public void testDeleteUserByName(String userName)
    {
        Response response = UserEndPoints.deleteUser(userName);
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
