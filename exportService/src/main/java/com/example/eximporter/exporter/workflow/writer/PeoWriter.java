package com.example.eximporter.exporter.workflow.writer;


import com.example.eximporter.exporter.configuration.CommonConfiguration;
import com.example.eximporter.exporter.model.xml.peo.OmnPlacementExport;
import com.example.eximporter.exporter.file.FileManager;
import com.example.eximporter.exporter.service.ftp.FtpService;
import com.example.eximporter.exporter.service.http.RestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.example.eximporter.exporter.controller.StepListener.PARTIALLY_FILE_NAME;
import static com.example.eximporter.exporter.controller.StepListener.PARTIALLY_PROCESSED_MESSAGE;


@Configuration
public class PeoWriter implements ItemWriter<OmnPlacementExport> {

    @Autowired
    private FtpService ftpService;
    @Autowired
    private FileManager fileManager;
    @Autowired
    private CommonConfiguration commonConfiguration;

    private static final Logger LOGGER = LoggerFactory.getLogger(PeoWriter.class);
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd.HH-mm", Locale.ENGLISH);
    private boolean isPartialSend = false;
    private String problemFileName;
    private StringBuilder messageInfo = new StringBuilder("Operation partial completed. Reason: \n");



    @BeforeStep
    public void initMessage() {
        isPartialSend = false;
    }

    @AfterStep
    public void sendMessage(StepExecution stepExecution) {
        if (isPartialSend) {
            stepExecution.getJobExecution().getExecutionContext().put(PARTIALLY_PROCESSED_MESSAGE,
                    new JobParameter(messageInfo.toString()));
            stepExecution.getJobExecution().getExecutionContext().put(PARTIALLY_FILE_NAME,
                    new JobParameter(problemFileName));
        }
    }

    @Override
    public void write(List<? extends OmnPlacementExport> omnPlacementExports) throws RestException {
        for (OmnPlacementExport omnPlacementExport : omnPlacementExports) {
            Date date = new Date();
            String fileName = "OMN_PlacementExportData_" + dateFormat.format(date);
            try {
                writeXml(omnPlacementExport, commonConfiguration.getPeoExportFolder() + fileName+ ".xml");
                processFile(fileName);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                throw new RestException("Error while process Peo export file", e);
            }
        }
    }

    private void writeXml(OmnPlacementExport omnPlacementExport, String fileName) throws JAXBException {
        LOGGER.info("Write XML");
        File file = new File(fileName);
        JAXBContext jaxbContext = JAXBContext.newInstance(OmnPlacementExport.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(omnPlacementExport, file);
    }

    private void processFile(String fileName) {
        try {
            LOGGER.info("Check-in file and copy to Ftp:{} ", fileName);
            fileManager.processFile(fileName, commonConfiguration.getPeoExportFolder());
            ftpService.copyFileToFtp(fileName, commonConfiguration.getPeoDestinationFolder());
        } catch (RestException e) {
            LOGGER.error("Problem with processing xml file {}. Send partial completed message", fileName, e);
            isPartialSend = true;
            problemFileName = fileName;
            messageInfo.append("Problem with processing xml file ").append(fileName).append("\n").append(e.getMessage());
        }
    }


}



