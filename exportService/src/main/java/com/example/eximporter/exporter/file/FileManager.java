package com.example.eximporter.exporter.file;

import com.example.eximporter.exporter.helper.ZipHelper;
import com.example.eximporter.exporter.service.http.RestException;
import com.example.eximporter.exporter.service.js.JSParameter;
import com.example.eximporter.exporter.service.js.checkin.CheckinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.EnumMap;
import java.util.Map;

@Component
public class FileManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileManager.class);
    @Autowired
    private ZipHelper zipHelper;
    @Autowired
    private CheckinService checkinService;

    public void processFile(String fileName, String folder) throws RestException {
        try {
            zipHelper.zipFile(folder+fileName);
            deleteIfExists(folder+fileName + ".xml");
            checkinFile(folder+fileName + ".zip");
        } catch (RestException e) {
            LOGGER.error("Error while processing file {}", fileName, e);
            throw new RestException("Error while processing file " + fileName, e);
        }
    }

    private void deleteIfExists(String fileName) throws RestException {
        try {
            Files.deleteIfExists(Paths.get(fileName));
            LOGGER.info("Deletion successful: {}", fileName);
        } catch (Exception e) {
            LOGGER.error("Error while delete file {}", fileName);
            throw new RestException("Error while delete file " + fileName, e);
        }
    }

    public void checkinFile(String file) throws RestException {
        try {
            Map<JSParameter, String> parameters = new EnumMap<>(JSParameter.class);
            parameters.put(JSParameter.FILE_NAME, file + ".zip");
            checkinService.call(parameters);
            LOGGER.info("File {} is checked successfully ", file);
        } catch (Exception e) {
            LOGGER.error("File is not checked in folder ", file);
            throw new RestException("File is not checked in folder " + file, e);
        }

    }


}
