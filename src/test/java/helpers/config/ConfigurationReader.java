package helpers;

import config.Configurations;
import org.json.JSONObject;
import java.io.IOException;

public class ConfigurationReader {

    private final String configurationFile = "application.json";
    private final String configurationDirectory = "./config/";
    private ProjectFileReader projectFileReader = null;

    public ConfigurationReader(){
        this.projectFileReader = new ProjectFileReader(this.configurationFile, this.configurationDirectory);
    }

    private JSONObject loadConfigurations() throws IOException {
        return projectFileReader.returnFileContentsAsJson();
    }

    public JSONObject getKeysOfConfigurationFiles() throws IOException {
        return this.loadConfigurations().getJSONObject("configuration_files");
    }

    public String getConfigurationFilesDirectory() throws IOException {
        return this.getKeysOfConfigurationFiles().getString("directory");
    }

    public String getMappingFileAddress() throws IOException {
        return this.getKeysOfConfigurationFiles().getString("directory") +
            this.getKeysOfConfigurationFiles().getString("services_mapping");
    }

    public JSONObject getKeysForTestFiles() throws IOException {
        return this.loadConfigurations().getJSONObject("test_data_files");
    }

    public String getTestFilesDirectory() throws IOException  {
        return this.getKeysForTestFiles().getString("directory");
    }

}
