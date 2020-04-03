package com.automation;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.automation.Constants.LOGIN_API;
import static com.automation.Constants.REGISTRATION_API;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by shirisha
 * since  4/3/20.
 */
public class SignUpAndSignInFlowTest {

    public HelperClass helperClass;
    private String name;
    private String password;
    private String token;

    @BeforeTest
    public void setUp() throws IOException, InterruptedException{
        helperClass = new HelperClass();
        name= "Test" + helperClass.getUUID();
        password="Test" +helperClass.getUUID();
    }

    @Test(priority = 0)
    public void registrationOfUser(){
        Response response =given().
                contentType(ContentType.JSON).
                body(helperClass.getRequestFixture("jsons", "sign-up.json").replace("$name",name).replace("$password",password)).
                log().all().
                expect().
                log().all().
                statusCode(200).
                body(containsString("token")).
                when().
                post(REGISTRATION_API);
        token = response.path("token");

    }

    @Test(priority = 1,description = "Here token from sign-up api is used as query param , but there is a bug in api, where it is not validating i")
    public void login(){
        given().
                contentType(ContentType.JSON).
                queryParam("token",token).
                body(helperClass.getRequestFixture("jsons", "sign-in.json").replace("$name",name).replace("$password",password)).
                log().all().
                expect().
                log().all().
                statusCode(200).
                body(containsString("token")).
                when().
                post(LOGIN_API);
    }
}
