package com.mi.tlv.parser.operations;

import com.mi.tlv.parser.annotation.OperationName;

@OperationName(name = ReplaceOperation.OPERATION_NAME)
public class ReplaceOperation extends Operation {

	private static final String FIXED_REPLACE_STRING = "THIS STRING";

	static final String OPERATION_NAME = "REPLCE";

	@Override
	protected final String internalProcess(String[] valueSet) {
		return FIXED_REPLACE_STRING;
	}

}
