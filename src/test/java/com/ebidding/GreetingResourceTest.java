package com.ebidding;

import com.ebidding.model.Company;
import com.ebidding.model.Contractor;
import com.ebidding.model.Project;
import com.ebidding.model.User;
import com.ebidding.util.Utility;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("api/hello")
          .then()
             .statusCode(200)
             .body(is("Hello RESTEasy"));
    }

    @Test
    public void testSaveProject(){
        Project project = new Project();
        project.setName("TestName");
        project.setDescription("TestDescription");
        given().when().queryParam("project", project).get("api/saveProject").then().statusCode(200);
    }

    @Test
    public void getProjects(){
        given().when().get("api/getProjects").then().statusCode(200);
    }

    @Test
    public void saveContractor(){
        Contractor contractor = new Contractor();
        contractor.setName("Test Contractor");
        contractor.setDescription("Test Description");
        contractor.setCompanyId("61cce8acbf285834f036cf9b");
        given().when().queryParam("contractor", contractor).get("api/saveContractor").then().statusCode(200);
    }

    @Test
    public void saveUser(){
        User user = new User();
        user.setName("Test User");
        user.setCompanyId("61cce8acbf285834f036cf9b");
        given().when().queryParam("user", user).get("api/saveUser").then().statusCode(200);
    }

    @Test
    public void saveCompany(){
        Company company = new Company();
        company.setName("Test Company");
    }
    @Test
    public void sendEmail(){
        Utility utility = new Utility();
        utility.sendEmail("junaidp@gmail.com", "test","test body");
    }

}