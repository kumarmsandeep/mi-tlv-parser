package com.mi.tlv.parser;

import java.io.IOException;
import java.util.Scanner;

import com.mi.tlv.parser.operations.Operation;

public class Main {

	private OperationFactory operationFactory = null;

	public Main() throws ClassNotFoundException, IOException {
		operationFactory = new OperationFactory("com.mi.tlv.parser");
	}

	public String parseTLVInput(String type, int length, String value)
			throws InstantiationException, IllegalAccessException {
		Operation operation = operationFactory.getOperation(type);
		if (operation != null) {
			return type + Operation.DELIMETER + operation.process(value);
		} else {
			return "Type not valid";
		}
	}

	public void parseInput(String input) throws InstantiationException, IllegalAccessException {
		input = input.replace(Operation.DELIMETER, "");
		int i = 0;

		while (i < input.length()) {
			for (int j = 0; j < 3; j++) {
				String type = input.substring(i, i + 6);
				i += 6;
				j++;
				int length = Integer.parseInt(input.substring(i, i + 4));
				i += 4;
				j++;
				String value = input.substring(i, i + length);
				i += length;
				j++;
				System.out.println(parseTLVInput(type, length, value));
			}
		}
	}

	public void start() throws InstantiationException, IllegalAccessException {
		Scanner sc = new Scanner(System.in);

		try {
			while (sc.hasNextLine()) {
				String input = sc.nextLine();
				if (input != null || !input.equals(" "))
					parseInput(input);
			}
		} finally {
			sc.close();
		}
	}

	public static void main(String[] args)
			throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException {
		Main main = new Main();
		main.start();
	}
}
