package helpers.config;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMethod;
import helpers.constants.Configurations.ServiceResources;
import helpers.constants.Configurations.CommonKeys;
import helpers.config.ConfigurationReader;
import helpers.ProjectFileReader;

public class MappingReader {
    private ResourceReader _configReader;
    private ConfigurationReader _configurationReader;
    private ProjectFileReader _fileReader;
    String _mappingFile = null;

    public enum Resources {
        PEOPLE("people"),
        FILMS("films");
        final String resourceName;

        Resources(String resourceName) {
            this.resourceName = resourceName;
        }
    }

    public MappingReader() throws IOException {
        _configurationReader = new ConfigurationReader();
        //String _mappingFile = "mapping.json";
        _mappingFile = _configurationReader.getKeysOfConfigurationFiles()
            .getString(CommonKeys.SERVICES_MAPPING.toString());
        _fileReader = new ProjectFileReader(_mappingFile, _configurationReader.getConfigurationFilesDirectory());
        _configReader = new ResourceReader(_mappingFile, ResourceReader.ProjectResourceDirectory.CONFIG);
    }

    private String getRawConfig(){
        return _configReader.getFileContents();
    }

    private JSONObject getConfig(@NotNull Resources resource){
        return new JSONObject(this.getRawConfig()).getJSONObject(resource.resourceName);
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

    public String getResource(Resources resource){
        return this.getConfig(resource).getString("resource");
    }

    public String getServiceURI() {
        return new JSONObject(this.getRawConfig()).getString("service_URI");
    }

    //public String getServiceURI(){ return _configurationReader.getKeysOfConfigurationFiles().getString(); }

    public String getFullResourceURI(Resources resource){
        return this.getServiceURI() + this.getResource(resource);
    }

    public int getExpectedResponseCode(Resources resource, HttpMethod method){
        return this.getConfig(resource).getJSONObject(String.valueOf(method))
                .getInt("expected_response_code");
    }

    public ArrayList<String> getMandatoryFieldNames(Resources resource, RequestMethod method, boolean isSingle){
        ArrayList<String> fieldNames = new ArrayList<>();

        for(JSONObject mandatoryField: getMandatoryFieldsByMethod(resource, method, isSingle)){
            fieldNames.add(mandatoryField.getString("field_name"));
        }
        return fieldNames;
    }





}
