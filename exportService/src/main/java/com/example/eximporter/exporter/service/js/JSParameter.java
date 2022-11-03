package com.example.eximporter.exporter.service.js;

public enum JSParameter
{
	MESSAGE("message"), JOURNAL_ID("journalId"),JOURNAL_ENTITY("journalEntity"),  MSG_TEMPLATE("msgTemplate"),FILE_NAME("exportFile");
	private String identifier;

	JSParameter(String identifier)
	{
		this.identifier = identifier;
	}

	@Override
	public String toString()
	{
		return identifier;
	}

	public static JSParameter getValue(String identifier)
	{
		for (JSParameter jsParameter : JSParameter.values())
		{
			if (jsParameter.identifier.equals(identifier))
			{
				return jsParameter;
			}
		}
		throw new IllegalArgumentException("There is no enumerated value with the given identifier: " + identifier);
	}
}
