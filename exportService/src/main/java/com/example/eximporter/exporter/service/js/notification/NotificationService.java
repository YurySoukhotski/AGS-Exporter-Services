package com.example.eximporter.exporter.service.js.notification;

import com.example.eximporter.exporter.service.js.JSService;
import org.springframework.stereotype.Service;

@Service
public class NotificationService extends JSService {
	@Override
    protected String getScriptName() {
		return commonConfiguration.getNotificationScriptName();
	}
}
