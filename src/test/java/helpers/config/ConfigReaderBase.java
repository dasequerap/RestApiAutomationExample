package helpers.config;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

class ConfigReaderBase {

    private String fileName;
    private String fileContents;
    private final String configDirectory = "./config/";

    private InputStream getFile(String fileName){
        return getClass().getClassLoader()
                .getResourceAsStream(configDirectory + fileName);
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
