package com.automation;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;
import static com.automation.Constants.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by shirisha
 * since  4/3/20.
 */
public class SignUpTest {

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
    public void registrationOfUserWithExistingEmail(){
        given().
                contentType(ContentType.JSON).
                body(helperClass.getRequestFixture("jsons", "sign-up.json").replace("$name",name).replace("$password",password)).
                log().all().
                expect().
                log().all().
                statusCode(422).
                body("error.email",is("Another user with the given email already exists")).
                body("success",is(false)).
                when().
                post(REGISTRATION_API);
    }

    @Test(priority = 2)
    public void signUpWithEmptyBody(){
        given().
                contentType(ContentType.JSON).
                log().all().
                expect().
                log().all().
                statusCode(422).
                body("error.form", is("Input not in required format")).
                body("success",is(false)).
                when().
                post(REGISTRATION_API);

    }


    @Test(priority = 3)
    public void signUpWithoutName(){
        given().
                contentType(ContentType.JSON).
                log().all().
                expect().
                log().all().
                statusCode(422).
                body("error.form", is("Input not in required format")).
                body("success",is(false)).
                when().
                post(REGISTRATION_API);

    }
    @Test(priority = 4)
    public void signUpWithoutEmail(){
        given().
                contentType(ContentType.JSON).
                log().all().
                expect().
                log().all().
                statusCode(422).
                body("error.form", is("Input not in required format")).
                body("success",is(false)).
                when().
                post(REGISTRATION_API);

    }

    @Test(priority = 5)
    public void signUpWithoutpassword(){
        given().
                contentType(ContentType.JSON).
                log().all().
                expect().
                log().all().
                statusCode(422).
                body("error.form", is("Input not in required format")).
                body("success",is(false)).
                when().
                post(REGISTRATION_API);

    }

}
