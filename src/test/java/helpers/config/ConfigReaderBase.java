package helpers.config;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ConfigReaderBase {

    private File file;
    private String fileName;
    private String fileContents;
    protected String configDirectory = "./config/";

    protected InputStream getFile(String fileName){
        return getClass().getClassLoader()
                .getResourceAsStream(configDirectory + fileName);
    }

    protected String getFileContents() { return this.fileContents; }

    protected void setFileName(String fileName){ this.fileName = fileName; }

    protected void setFileContents() throws IOException {
        this.fileContents = IOUtils.toString(this.getFile(this.fileName), StandardCharsets.UTF_8);
    }

    protected JSONObject getJsonConfig(){
        return new JSONObject(this.getFileContents());
    }

}
