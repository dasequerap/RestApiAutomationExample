package helpers.config;

import helpers.constants.Configurations;
import helpers.ProjectFileReader;
import org.json.JSONObject;
import java.io.IOException;

public class ConfigurationReader {

    private final ProjectFileReader projectFileReader;

    public ConfigurationReader(){
        String configurationFile = "application.json";
        String configurationDirectory = "./config/";
        this.projectFileReader = new ProjectFileReader( configurationFile, configurationDirectory );
    }

    private JSONObject loadConfigurations() throws IOException {
        return projectFileReader.returnFileContentsAsJson();
    }

    public JSONObject getKeysOfConfigurationFiles() throws IOException {
        return this.loadConfigurations().getJSONObject(Configurations.CommonKeys.CONFIGURATION_FILES.toString());
    }

    protected JSONObject getKeysForTestFiles() throws IOException {
        return this.loadConfigurations().getJSONObject(Configurations.CommonKeys.TEST_DATA_FILES.toString());
    }

    protected String getConfigurationFilesDirectory() throws IOException {
        return this.getKeysOfConfigurationFiles().getString(Configurations.CommonKeys.DIRECTORY.toString());
    }

    protected String getMappingFileAddress() throws IOException {
        return this.getConfigurationFilesDirectory() + this.getKeysOfConfigurationFiles().getString(
                Configurations.CommonKeys.SERVICES_MAPPING_FILE.toString());
    }

    protected String getTestFilesDirectory() throws IOException  {
        return this.getKeysForTestFiles().getString(Configurations.CommonKeys.DIRECTORY.toString());
    }

}
