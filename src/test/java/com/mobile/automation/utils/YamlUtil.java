package com.mobile.automation.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

@Component
public class YamlUtil {
    @SuppressWarnings("unchecked")
    public <T extends Object> T getClassByYaml(Class clazz, String yamlFileSource) {
        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass()
            .getClassLoader()
            .getResourceAsStream(yamlFileSource);
        return  (T) yaml.loadAs(inputStream, clazz);
    }

    public void yamlWriter(Map<String,String> data , String file) {
        Yaml yaml = new Yaml();
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            yaml.dump(data);
            yaml.dump(data, writer);
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


     public <T extends Object> T yamlReader(Class clazz, String file) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
              return (T) mapper.readValue(new File(file), clazz);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<String, String> loadYamlFileContent(String key , String fileName){
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
        Map<String, Map<String , String>> seatMap = new Yaml().load(inputStream);
        return seatMap.get(key);
    }

    public  <T extends Object> void yamlWriterNew( T data , String file) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper = new ObjectMapper(new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER));
        try {
            mapper.writeValue(new File(file), data);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}