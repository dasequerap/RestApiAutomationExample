package tests.people;

import java.io.IOException;
import helpers.config.MappingReader;
import tests.BaseTests;
import views.PeopleView;
import org.junit.jupiter.api.*;
import org.springframework.http.HttpMethod;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PeopleSmokeTests extends BaseTests {
    private final PeopleView peopleView = new PeopleView();
    private final MappingReader mappingReader;

    PeopleSmokeTests() throws IOException {
        mappingReader = new MappingReader();
    }

    @Test
    @Order(1)
    @DisplayName("Check response after a query to people resource with GET method")
    void getPeopleTest() {
        this.setCurrentResponse( peopleView.getPeople( 1 ) );
        this.validateResponseStatusByCode(
                mappingReader.getExpectedResponseCode(MappingReader.Resources.PEOPLE, HttpMethod.GET));
        this.validateContentTypeIsJson();
    }

    @Order(2)
    @Test
    @DisplayName("Check successful response from people resource with HEAD method")
    void getPeopleResponseWithHeadMethodTest() throws IOException {
        this.setCurrentResponse(peopleView.head());
        this.validateResponseStatusByCode(
                mappingReader.getExpectedResponseCode( MappingReader.Resources.PEOPLE, HttpMethod.HEAD ) );
    }

    @Order(3)
    @Test
    @DisplayName("Check successful response from people resource with OPTIONS method")
    void getPeopleResponseWithOptionsMethodTest() throws IOException {
        this.setCurrentResponse(peopleView.options());
        this.validateResponseStatusByCode(
                mappingReader.getExpectedResponseCode(MappingReader.Resources.PEOPLE, HttpMethod.OPTIONS));
        this.validateContentTypeIsJson();
    }

    @Order(4)
    @Test
    @DisplayName("Check not allowed response from people resource with POST method")
    void getPeopleResponseWithPostMethodTest() throws IOException {
        this.setCurrentResponse(peopleView.post());
        this.validateResponseStatusByCode(
                mappingReader.getExpectedResponseCode(MappingReader.Resources.PEOPLE, HttpMethod.POST));
        this.validateContentTypeIsJson();
    }
}
