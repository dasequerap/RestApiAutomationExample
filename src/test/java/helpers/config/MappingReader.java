package helpers.config;

import helpers.ResourceReader;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMethod;

public class MappingReader {
    private ResourceReader _configReader;

    public enum Resources {
        PEOPLE("people"),
        FILMS("films");
        final String resourceName;

        Resources(String resourceName) {
            this.resourceName = resourceName;
        }
    }

    public MappingReader() throws IOException {
        String _mappingFile = "mapping.json";
        _configReader = new ResourceReader( _mappingFile, ResourceReader.ProjectResource.CONFIG);
        JSONObject mappingConfig = _configReader.getJsonConfig();
        JSONObject peopleConfig = mappingConfig.getJSONObject("people");
    }

    private String getRawConfig(){
        return _configReader.getFileContents();
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

    public String getFullResourceURI(Resources resource){
        return this.getServiceURI() + this.getResource(resource);
    }

    public int getExpectedResponseCode(Resources resource, HttpMethod method){
        return this.getConfig(resource).getJSONObject(String.valueOf(method))
                .getInt("expected_response_code");
    }

    public JSONArray getQueryParametersByMethod(Resources resource, RequestMethod method){
        return this.getConfig(resource).getJSONObject(String.valueOf(method))
                .getJSONArray("query_parameters");
    }

    private JSONObject getRequestQueryParameters(Resources resource, RequestMethod method, boolean isSingle){
        String fieldType = (isSingle) ? "fields_single_result" : "fields_multiple_results";
        return this.getConfig(resource).getJSONObject(String.valueOf(method))
                .getJSONObject(fieldType);
    }

    private JSONObject getFieldConfigurationByMethod(String fieldName, Resources resource, RequestMethod method, boolean isSingle){
        return this.getRequestQueryParameters(resource, method, isSingle).getJSONObject(fieldName);
    }

    private ArrayList<JSONObject> getMandatoryFieldsByMethod(Resources resource, RequestMethod method, boolean isSingle){
        JSONObject requestFields = this.getRequestQueryParameters(resource, method, isSingle);
        ArrayList<JSONObject> mandatoryFields = new ArrayList<>();

        for (Iterator<String> keys = requestFields.keys(); keys.hasNext(); ) {
            String key = keys.next();
            JSONObject field = this.getFieldConfigurationByMethod(key, resource, method, isSingle);
            mandatoryFields.add(field);
        }
        return mandatoryFields;
    }

    public ArrayList<String> getMandatoryFieldNames(Resources resource, RequestMethod method, boolean isSingle){
        ArrayList<String> fieldNames = new ArrayList<>();

        for(JSONObject mandatoryField: getMandatoryFieldsByMethod(resource, method, isSingle)){
            fieldNames.add(mandatoryField.getString("field_name"));
        }
        return fieldNames;
    }
}
