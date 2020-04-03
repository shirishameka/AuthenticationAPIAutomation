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
public class LoginTest {

    public HelperClass helperClass;
    private String name;
    private String password;

    @BeforeTest
    public void setUp() throws IOException, InterruptedException{
        helperClass = new HelperClass();
        name= "Test" + helperClass.getUUID();
        password="Test" +helperClass.getUUID();
    }

    @Test(priority = 0)
    public void registrationOfUser(){
        given().
                contentType(ContentType.JSON).
                body(helperClass.getRequestFixture("jsons", "sign-up.json").replace("$name",name).replace("$password",password)).
                log().all().
                expect().
                log().all().
                statusCode(200).
                body(containsString("token")).
                when().
                post(REGISTRATION_API);
    }


    @Test(priority = 1)
    public void login(){
        given().
                contentType(ContentType.JSON).
                body(helperClass.getRequestFixture("jsons", "sign-in.json").replace("$name",name).replace("$password",password)).
                log().all().
                expect().
                log().all().
                statusCode(200).
                body(containsString("token")).
                when().
                post(LOGIN_API);
    }

    @Test(priority = 2)
    public void loginWithEmptyBody(){
        given().
                contentType(ContentType.JSON).
                log().all().
                expect().
                log().all().
                statusCode(422).
                body("error.form",is("Input not in required format")).
                body("success",is(false)).
                when().
                post(LOGIN_API);
    }
    @Test(priority = 3)
    public void loginWithInvalidEmaid(){
        given().
                contentType(ContentType.JSON).
                body(helperClass.getRequestFixture("jsons", "sign-in.json").replace("$name","1234").replace("$password",password)).
                log().all().
                expect().
                log().all().
                statusCode(422).
                body("error.email",is("No user account exists with the given email.")).
                body("success",is(false)).
                when().
                post(LOGIN_API);
    }

    @Test(priority = 4)
    public void loginWithInvalidPassword(){
        given().
                contentType(ContentType.JSON).
                body(helperClass.getRequestFixture("jsons", "sign-in.json").replace("$name",name).replace("$password","1234")).
                log().all().
                expect().
                log().all().
                statusCode(422).
                body("error.password",is("Invalid Password")).
                body("success",is(false)).
                when().
                post(LOGIN_API);
    }

    @Test(priority = 5)
    public void loginWithoutEmailId(){
        given().
                contentType(ContentType.JSON).
                log().all().
                expect().
                log().all().
                statusCode(422).
                body("error.form",is("Input not in required format")).
                body("success",is(false)).
                when().
                post(LOGIN_API);
    }

    @Test(priority = 6)
    public void loginWithoutPassword(){
        given().
                contentType(ContentType.JSON).
                log().all().
                expect().
                log().all().
                statusCode(422).
                body("error.form",is("Input not in required format")).
                body("success",is(false)).
                when().
                post(LOGIN_API);
    }


}
