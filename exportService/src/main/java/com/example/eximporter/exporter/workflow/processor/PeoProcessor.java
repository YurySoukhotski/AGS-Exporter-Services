package com.example.eximporter.exporter.workflow.processor;

import com.example.eximporter.exporter.model.xml.peo.OmnPlacementExport;
import com.example.eximporter.exporter.model.extended.ExtendedPeo;
import com.example.eximporter.exporter.model.xml.peo.Placement;
import com.example.eximporter.exporter.model.xml.peo.PlacementDeleted;
import com.example.eximporter.exporter.service.converter.PeoConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class PeoProcessor implements ItemProcessor<List<ExtendedPeo>, OmnPlacementExport> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PeoProcessor.class);

    @Autowired
    private PeoConverter peoConverter;

    @Override
    public OmnPlacementExport process(List<ExtendedPeo> extendedPeos) throws Exception {
        OmnPlacementExport omnPlacementExport = new OmnPlacementExport();
        List<Placement> placements = new ArrayList<>();
        List<PlacementDeleted> placementDeleted = new ArrayList<>();
        for (ExtendedPeo extendedPeo : extendedPeos) {
            if (extendedPeo.getJournal().getChangeOperation() != null && !"DELETED".equalsIgnoreCase(extendedPeo.getJournal().getChangeOperation())) {
                placements.add(peoConverter.convert(extendedPeo));
            } else {
                placementDeleted.add(peoConverter.convertDeleted(extendedPeo));
            }
        }
        omnPlacementExport.setPlacement(placements);
        omnPlacementExport.setPlacementDeleted(placementDeleted);
        LOGGER.info("Do smth with Peo and return XMl string");
        return omnPlacementExport;
    }
}
