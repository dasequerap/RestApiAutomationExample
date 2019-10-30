package tests;

import io.restassured.path.json.JsonPath;
import models.PeopleModel;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import views.PeopleView;

import java.util.List;

class PeopleTest {

    private String nextURL;
    private JsonPath currentResponse;
    private List<PeopleModel> peopleObjects;
    private PeopleModel testPeople = null;

    /* Test Feature Branch */
    @Test
    public void checkPeopleResponseOkTest() {
        PeopleView peopleView = new PeopleView();
        peopleView.getPeople(1).log().all().assertThat().statusCode(HttpStatus.SC_OK);
    }
}