package bh.ghareeb.BackendPractical.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Generic service that manage an entity within a JSON file
 * @param <T> The record/Model type that will be managed
 */
@Service
public class JsonDatabaseService<T> {
    private String JsonPath;

    private static final Logger logger = LoggerFactory.getLogger(JsonDatabaseService.class);
    private ObjectMapper objectMapper = new ObjectMapper();
    private List<T> recordList = new CopyOnWriteArrayList<>();

    public JsonDatabaseService() {

    }


    public JsonDatabaseService(String JsonFilePath, Class<T> type) {
        this.JsonPath = JsonFilePath;
        this.readJson();
    }


    private void readJson() {
        try{
            File file = new File(JsonPath);
            if (file.exists()) {

                this.recordList.addAll(
                        objectMapper.readValue(file,new TypeReference<List<T>>() {})
                );
            }
        } catch (Exception e) {
            logger.error("An error occurred: {}", e.getMessage(), e);
        }

        logger.info(this.recordList.toString());
    }


    public List<T> getRecordList() {
        return recordList;
    }
}
