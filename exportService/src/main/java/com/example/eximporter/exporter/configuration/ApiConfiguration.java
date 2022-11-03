package com.example.eximporter.exporter.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * API configuration
 */
@Configuration
public class ApiConfiguration
{
	@Value("${default.api.url}")
	private String apiUrl;
	@Value("${default.api.basic.auth}")
	private String apiBasicAuth;
	@Value("${default.api.project.path}")
	private String apiProjectPath;
	@Value("${default.api.peo.path}")
	private String apiPeoPath;
	@Value("${default.api.journal.path}")
	private String apiJournalPath;
	@Value("${default.auth.path}")
	private String authPath;


	public String getApiJournalPath() {
		return new StringBuilder().append(apiUrl).append(apiJournalPath).toString();
	}

	/**
	 * Gets URL for project API
	 * @return URL for project API
	 */
	public String getProjectApiUrl()
	{
		return new StringBuilder().append(apiUrl).append(apiProjectPath).toString();
	}

	/**
	 * Gets URL for PEO API
	 * @return URL for PEO API
	 */
	public String getPeoApiUrl()
	{
		return new StringBuilder().append(apiUrl).append(apiPeoPath).toString();
	}


	/**
	 * Gets URL for Auth API
	 * @return URL for Auth API
	 */
    public String getAuthPath() {
        return new StringBuilder().append(apiUrl).append(authPath).toString();
    }

	public String getApiBasicAuth() {
		return apiBasicAuth;
	}
}
