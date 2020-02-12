import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.HashMap;
import io.restassured.RestAssured;
import org.jsoup.Jsoup;
import views.BaseView;

class ApiTestSample {
    public static void main(String[] args) throws IOException, URISyntaxException {
        String serviceUrl = "https://swapi.co/api/{resource}/{peopleId}/";
        String serviceBaseUrl = "https://swapi.co/api";
        String encodedURL = URLDecoder.decode(serviceUrl
                .replace("{resource}", "people")
                .replace("{peopleId}", "2"));
        HashMap<String, String> queryParams = new HashMap<>();
        HashMap<String, String> pathParams = new HashMap<>();
        BaseView testView = new BaseView();

        queryParams.put("person", "2");
        queryParams.put("page", "3");
        pathParams.put("resource", "people");
        pathParams.put("peopleId", "2");
        testView.setRequest(RestAssured.given().log().all());
        testView.setUri(serviceUrl);
        testView.setWaitURI(serviceBaseUrl);
        testView.buildWaitUrl(pathParams, queryParams);
        testView.get(pathParams, queryParams);
        /*PeopleView peopleURI = new PeopleView();
        String nextURL;
        JsonPath currentResponse;
        List<PeopleModel> peopleObjects;
        PeopleModel testPeople = null;


        peopleURI.getPeople(1).assertThat().contentType(ContentType.JSON).and()
                .statusCode(HttpStatus.SC_OK).and().body("count", equalTo(87));
        testPeople = peopleURI.getPeopleById(1).extract().as(PeopleModel.class);
        currentResponse = peopleURI.getPeople(1).extract().jsonPath();

        while (peopleURI.getNextUrl() != null) {
            peopleObjects = currentResponse.getList("results", PeopleModel.class);
            peopleObjects.forEach((peopleIndex) -> System.out.println(
                    peopleIndex.getName() + " : " + peopleIndex.getUrl()));
            if (peopleURI.getNextUrl() != null) {
                currentResponse = peopleURI.getPeople(peopleURI.getCurrentPage() + 1)
                        .extract().jsonPath();
            }
        }*/
    }

    static void wait(String url, int milliseconds) throws IOException {
        Jsoup.connect(url).timeout(milliseconds).get();
    }

    /*static void restAssuredTimeout() {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(500)
                .setConnectionRequestTimeout(500)
                .setSocketTimeout(500)
                .build();

        HttpClientConfig httpClientFactory = HttpClientConfig.httpClientConfig()
                .httpClientFactory(() -> HttpClientBuilder.create()
                        .setDefaultRequestConfig(requestConfig)
                        .build());

        RestAssured.config = RestAssured
                .config()
                .httpClient(httpClientFactory);
    }*/
}
