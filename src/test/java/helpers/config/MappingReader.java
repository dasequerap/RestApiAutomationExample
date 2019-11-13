package helpers.config;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMethod;

public class MappingReader extends ConfigReaderBase{

    public enum Resources {
        PEOPLE("people"),
        FILMS("films");
        final String resourceName;

        Resources(String resourceName) {
            this.resourceName = resourceName;
        }
    }

    public MappingReader() throws IOException {
        this.setFileName("mapping.json");
        this.setFileContents();
        JSONObject mappingConfig = this.getJsonConfig();
        JSONObject peopleConfig = mappingConfig.getJSONObject( "people" );
    }

    private String getRawConfig(){
        return this.getFileContents();
    }

    public String getServiceURI() {
        return new JSONObject(this.getRawConfig()).getString("service_URI");
    }

    private JSONObject getConfig(Resources resource){
        return new JSONObject(this.getRawConfig()).getJSONObject(resource.resourceName);
    }

    public String getResource(Resources resource){
        return this.getConfig(resource).getString("resource");
    }

    public int getExpectedResponseCode(Resources resource, HttpMethod method){
        return this.getConfig(resource).getJSONObject(String.valueOf(method))
                .getInt("expected_response_code");
    }

    public JSONArray getQueryParametersByMethod(Resources resource, RequestMethod method){
        return this.getConfig(resource).getJSONObject(String.valueOf(method))
                .getJSONArray("query_parameters");
    }

    public JSONObject getRequestQueryParameters(Resources resource, RequestMethod method){
        return this.getConfig(resource).getJSONObject(String.valueOf(method))
                .getJSONObject("fields");
    }

    public String getFullResourceURI(Resources resource){
        return this.getServiceURI() + this.getResource(resource);
    }
}
