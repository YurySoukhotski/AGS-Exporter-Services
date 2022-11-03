/**
 * Copyright (c) 2017 apollon GmbH+Co. KG All Rights Reserved.
 */
package com.example.eximporter.exporter.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Copyright 2017 apollon GmbH+Co. KG All Rights Reserved.
 * @author yury.soukhotski
 */
public class Journal
{
	private Long id ;
	@JsonProperty("changedOnDate")
	private String changedOnDate = null;
	@JsonProperty("changedBy")
	private String changedBy = null;
	@JsonProperty("changeOperation")
	private String changeOperation = null;
	@JsonProperty("entityId")
	private Long entityId = null;
	@JsonProperty("entityType")
	private String entityType = null;
	@JsonProperty("entityStatus")
	private String entityStatus = null;

	public Journal() {

	}

	public Journal(Long id, String changedOnDate, String changedBy, String changeOperation, Long entityId, String entityType, String entityStatus) {
		this.id = id;
		this.changedOnDate = changedOnDate;
		this.changedBy = changedBy;
		this.changeOperation = changeOperation;
		this.entityId = entityId;
		this.entityType = entityType;
		this.entityStatus = entityStatus;
	}

	public Long getId() {
		return id;
	}

	public String getChangedOnDate() {
		return changedOnDate;
	}

	public String getChangedBy() {
		return changedBy;
	}

	public String getChangeOperation() {
		return changeOperation;
	}

	public Long getEntityId() {
		return entityId;
	}

	public String getEntityType() {
		return entityType;
	}

	public String getEntityStatus() {
		return entityStatus;
	}
}
