package helpers.config;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class MappingReader extends ConfigReaderBase{

    private JSONObject mappingConfig;
    private JSONObject peopleConfig;

    public MappingReader() throws IOException {
        this.setFileName("mapping.json");
        this.setFileContents();
        mappingConfig = this.getJsonConfig();
        peopleConfig = mappingConfig.getJSONObject("people");
    }

    public String getRawConfig(){
        return this.getFileContents();
    }

    public JSONObject getPeopleConfig() { return this.peopleConfig; }

    public String getPeopleURI() {
        return this.getPeopleConfig().get("uri").toString();
    }

    public JSONArray getPeopleURIParams(){
        return this.getPeopleConfig().getJSONArray("queryParams");
    }
}
