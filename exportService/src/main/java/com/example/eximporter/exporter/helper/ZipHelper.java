package com.example.eximporter.exporter.helper;

import com.example.eximporter.exporter.service.http.RestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Component
public class ZipHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZipHelper.class);

    public void zipFile(String zipFile) throws RestException {

        try (FileOutputStream fos = new FileOutputStream(zipFile + ".zip")) {
            ZipOutputStream zipOut = new ZipOutputStream(fos);
            File fileToZip = new File(zipFile + ".xml");
            try (FileInputStream fis = new FileInputStream(fileToZip)) {
                ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                zipOut.putNextEntry(zipEntry);
                final byte[] bytes = new byte[1024];
                int length;
                while ((length = fis.read(bytes)) >= 0) {
                    zipOut.write(bytes, 0, length);
                }
                zipOut.close();
            }
        } catch (Exception e) {
            LOGGER.error("Error while zipping, {} ", zipFile);
            throw new RestException("Error occur while zipping " + zipFile, e);
        }
    }
}
