package tests;

import java.io.IOException;
import org.junit.jupiter.api.*;
import helpers.config.MappingReader;
import org.springframework.http.HttpMethod;
import views.PeopleView;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SmokeTests extends BaseTests {
    private final PeopleView peopleView = new PeopleView();
    private final MappingReader mappingReader;

    SmokeTests() throws IOException {
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
    void getPeopleResponseWithHeadMethodTest() {
        this.setCurrentResponse( peopleView.getHead() );
        this.validateResponseStatusByCode(
                mappingReader.getExpectedResponseCode( MappingReader.Resources.PEOPLE, HttpMethod.HEAD ) );
    }

    @Order(3)
    @Test
    @DisplayName("Check successful response from people resource with OPTIONS method")
    void getPeopleResponseWithOptionsMethodTest() {
        this.setCurrentResponse( peopleView.getOptions() );
        this.validateResponseStatusByCode(
                mappingReader.getExpectedResponseCode(MappingReader.Resources.PEOPLE, HttpMethod.OPTIONS));
        this.validateContentTypeIsJson();
    }

    @Order(4)
    @Test
    @DisplayName("Check not allowed response from people resource with POST method")
    void getPeopleResponseWithPostMethodTest() {
        this.setCurrentResponse(peopleView.post());
        this.validateResponseStatusByCode(
                mappingReader.getExpectedResponseCode(MappingReader.Resources.PEOPLE, HttpMethod.POST));
        this.validateContentTypeIsJson();
    }
}
