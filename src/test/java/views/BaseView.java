package views;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.http.client.utils.URIBuilder;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.HttpStatusException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestMethod;
import static io.restassured.RestAssured.given;

public class BaseView {

    ValidatableResponse _apiResponse;
    String _URI;
    String _waitURI;
    private String _resource;
    private RequestSpecification _request;
    private String variable = null;
    private String parameterSet = null;
    private String _bodyRequest = "";

    public BaseView() { }

    private String getResource() { return this._resource; }

    public RequestSpecification getRequest(){ return this._request; }

    void setResource(String resource) {this._resource = resource; }

    public void setRequest(RequestSpecification request){
        this._request = request;
    }

    public void setUri(String URI){
        this._URI = URI;
    }

    public void setWaitURI(String _waitURI){
        this._waitURI = _waitURI;
    }

    private void addQueryParams(Map<String, String > queryParameters){
        for (Map.Entry<?, ?> variable : queryParameters.entrySet()) {
            this.setRequest( this.getRequest().queryParam( (String) variable.getKey(), variable.getValue() ) );
        }
    }

    public void buildWaitUrl(Map<String, ?> pathParameters, Map<String, String> queryParameters) throws URISyntaxException {
        Iterator<? extends Map.Entry<String, ?>> parametersIterator = pathParameters.entrySet().iterator();
        URIBuilder builder = new URIBuilder(_waitURI);
        StringBuilder pathString = new StringBuilder();

        while(parametersIterator.hasNext()){
            Map.Entry<?, ?> variable = parametersIterator.next();
            pathString.append( "/" ).append( variable.getValue().toString() );
        }

        pathString.append( "/" );
        builder.setScheme("https").setPath(builder.getPath() + pathString);
        parametersIterator = queryParameters.entrySet().iterator();

        while(parametersIterator.hasNext()){
            Map.Entry<?, ?> queryParameter = parametersIterator.next();
            builder.addParameter(queryParameter.getKey().toString(),
                    queryParameter.getValue().toString());
        }

        this.setWaitURI(builder.toString());
        System.out.println(builder.toString());
    }

    public void waitUri(int milliseconds, RequestMethod method) throws IOException {
        Connection request = Jsoup.connect(this._waitURI).timeout( milliseconds);
        try {
            switch (method) {
                case GET:
                    request.get();
                    break;
                case POST:
                    request.post();
                    break;
                case HEAD:
                    request.method( Connection.Method.HEAD).execute();
                    break;
                case OPTIONS:
                    request.method( Connection.Method.OPTIONS).execute();
                    break;
            }
        } catch (HttpStatusException e){
            System.out.println("Rejected request");
        }
    }

    public ValidatableResponse head() throws IOException {
        this.setWaitURI(_URI);
        this.waitUri(5000, RequestMethod.POST);
        return given().head(_URI).then().log().all();
    }

    public ValidatableResponse options() throws IOException {
        this.setWaitURI(_URI);
        this.waitUri(5000, RequestMethod.OPTIONS);
        return given().options( _URI ).then().log().all();
    }

    public ValidatableResponse post() throws IOException {
        this.setWaitURI( _URI );
        this.waitUri( 5000, RequestMethod.POST);
        return _request.body( _bodyRequest ).post( this.getResource()).then().log().all();
    }

    public ValidatableResponse get(Map<String, ?> pathParameters, Map<String, String> queryParameters) throws IOException {
        this.addQueryParams(queryParameters);
        this.waitUri(5000, RequestMethod.GET);
        return _request.get(_URI, pathParameters).then().log().all();
    }
}
