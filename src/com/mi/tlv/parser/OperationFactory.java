package com.mi.tlv.parser;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import com.mi.tlv.parser.annotation.OperationName;
import com.mi.tlv.parser.common.ClassFactory;
import com.mi.tlv.parser.operations.Operation;

public class OperationFactory {

	private Map<String, Class<? extends Operation>> operationClasses = new HashMap<String, Class<? extends Operation>>();
	private Map<String, Operation> operations = new HashMap<String, Operation>();

	public OperationFactory(String packageName) throws ClassNotFoundException, IOException {
		Class<? extends Operation>[] classes = ClassFactory.getClasses(packageName, Operation.class);
		if (classes != null) {
			for (Class<? extends Operation> clazz : classes) {
				Annotation annotation = clazz.getAnnotation(OperationName.class);
				if (annotation != null) {
					OperationName operationName = (OperationName) annotation;
					String value = operationName.name();
					if (value != null && !value.isEmpty()) {
						operationClasses.put(value, clazz);
					}
				}
			}
		}
	}

	public Operation getOperation(String operationName) throws InstantiationException, IllegalAccessException {
		Operation op = operations.get(operationName);
		if (op == null) {
			Class<? extends Operation> operationClass = operationClasses.get(operationName);
			if (operationClass != null) {
				synchronized (operationClass) {
					op = operations.get(operationName);
					if (op == null) {
						Operation newInstance = operationClass.newInstance();
						op = (Operation) newInstance;
						operations.put(operationName, op);
					}
				}
			}
		}
				
		return op;
	}

	public static void main(String[] args)
			throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException {
		OperationFactory of = new OperationFactory("com.mi.tlv.parser");
		System.out.println(of.getOperation("UPPRCS1").process("UPPRCS-0004-abcd"));
		System.out.println(of.getOperation("REPLCE"));
	}
}
