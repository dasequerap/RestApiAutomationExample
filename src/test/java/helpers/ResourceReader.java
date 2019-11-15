package helpers.config;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

class ResourceReader {

    private String fileName;
    private String fileContents;

    public enum ProjectResource {
        CONFIG("./config/"),
        TEST_DATA("./test_data/");
        final String resourceName;

        ProjectResource(String resourceName) {
            this.resourceName = resourceName;
        }
    }

    private InputStream getFile(String fileName){
        String configDirectory = "./config/";
        return getClass().getClassLoader()
                .getResourceAsStream( configDirectory + fileName);
    }

    private InputStream getFile(String fileName, ProjectResource resource){
        String configDirectory = resource.resourceName;
        return getClass().getClassLoader()
                .getResourceAsStream( configDirectory + fileName);
    }

    String getFileContents() { return this.fileContents; }

    void setFileName(String fileName){ this.fileName = fileName; }

    void setFileContents() throws IOException {
        this.fileContents = IOUtils.toString(this.getFile(this.fileName), StandardCharsets.UTF_8);
    }

    JSONObject getJsonConfig(){
        return new JSONObject(this.getFileContents());
    }

}
