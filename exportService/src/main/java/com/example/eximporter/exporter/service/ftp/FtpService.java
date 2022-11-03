package com.example.eximporter.exporter.service.ftp;

import com.example.eximporter.exporter.configuration.CommonConfiguration;
import com.example.eximporter.exporter.service.http.RestException;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Service for retrieving files from FTP
 */
@Service
public class FtpService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(FtpService.class);
    @Autowired
    private CommonConfiguration commonConfiguration;

    /**
     * Connect to FTP and copy all files to ftp folder
     */
    public void copyFileToFtp(String fileName, String destinationFolder) throws RestException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.configure(new FTPClientConfig(FTPClientConfig.SYST_UNIX));
        ftpClient.enterLocalPassiveMode();
        try {
            ftpClient.connect(commonConfiguration.getHost());
            ftpClient.login(commonConfiguration.getUserName(), commonConfiguration.getUserPassword());
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            InputStream input = new FileInputStream(new File(fileName));
            ftpClient.storeFile(destinationFolder + fileName, input);
        } catch (IOException e) {
            LOGGER.error("File not found", e);
            throw new RestException("Problem while copy file to ftp", e);
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException e) {
                LOGGER.error("File not found", e);
            }
        }
    }

}
