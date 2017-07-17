package com.mi.tlv.parser.operations;

import com.mi.tlv.parser.annotation.OperationName;

@OperationName(name = UpperCaseOperation.OPERATION_NAME)
public class UpperCaseOperation extends Operation {

	static final String OPERATION_NAME = "UPPRCS";

	@Override
	public String internalProcess(String[] values) {
		return values[0].toUpperCase();
	}

}
