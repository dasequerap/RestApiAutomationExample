package helpers.config;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMethod;

public class MappingReader extends ConfigReaderBase{

    private JSONObject mappingConfig;
    private JSONObject peopleConfig;
    public enum Resources {
        PEOPLE("people"),
        FILMS("films");
        public String resourceName = "";

        Resources(String resourceName) {
            this.resourceName = resourceName;
        }
    }

    public MappingReader() throws IOException {
        this.setFileName("mapping.json");
        this.setFileContents();
        mappingConfig = this.getJsonConfig();
        peopleConfig = mappingConfig.getJSONObject("people");
    }

    public String getRawConfig(){
        return this.getFileContents();
    }

    public String getServiceURI() {
        return new JSONObject(this.getRawConfig()).getString("service_URI");
    }

    public JSONObject getConfig(Resources resource){
        return new JSONObject(this.getRawConfig()).getJSONObject(resource.resourceName);
    }

    public String getResource(Resources resource){
        return this.getConfig(resource).getString("resource");
    }

    public JSONArray getQueryParametersByMethod(Resources resource, RequestMethod method){
        switch(method){
            case GET:
                return this.getConfig(resource).getJSONObject("get_parameters")
                   .getJSONArray("query_parameters");
            default:
                return null;
        }
    }

    public JSONObject getRequestQueryParameters(Resources resource, RequestMethod method){
        switch(method){
            case GET:
                return this.getConfig(resource).getJSONObject("get_parameters")
                    .getJSONObject("fields");
            default:
                return null;
        }
    }

    public String getFullResourceURI(Resources resource){
        return this.getServiceURI() + this.getResource(resource);
    }
}
