package com.automation;

import java.util.Random;
import java.util.UUID;

import static io.dropwizard.testing.FixtureHelpers.fixture;
//import static io.dropwizard.testing.FixtureHelpers.fixture;

/**
 * Created by shirisha
 * since  4/3/20.
 */
public class HelperClass {

    public String getRequestFixture(String folderName, String fileName) {
        return fixture(folderName + "/" + fileName);
    }

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }


    public String getRandomId(){
        return UUID.randomUUID().toString();
    }





}
