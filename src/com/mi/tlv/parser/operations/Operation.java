package com.mi.tlv.parser.operations;

public abstract class Operation {

	public static final String DELIMETER = "-";

	public String process(String valueSet) {
		return internalProcess(valueSet.split(DELIMETER));
	}

	protected abstract String internalProcess(String[] values);
}
