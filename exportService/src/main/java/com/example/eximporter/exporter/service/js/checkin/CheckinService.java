package com.example.eximporter.exporter.service.js.checkin;

import com.example.eximporter.exporter.service.js.JSService;
import org.springframework.stereotype.Service;

/**
 * Checkin files
 */
@Service
public class CheckinService extends JSService {
    @Override
    protected String getScriptName() {
        return commonConfiguration.getCheckinScriptName();
    }
}
