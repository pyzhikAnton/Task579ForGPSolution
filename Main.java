import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
	
	/**
	 * ��������� ��� ���������� ��������������������� ����� �� ������� ������������������,
	 * ��� ������� ������ ����� ����������.
	 * ��������� ���� ���������� ����� ����� (� ��������� [-10000;10000]
	 * � ���������� n (1<=n<=10000) � ���������� �� � ���� txt/input.txt.
	 * ��������� � ���� ���� ����� - ������ ������ ���������� ��������� �
	 * ���������� ���������������������, ������ - ������ ��������� (������� � 1!).
	 * ��������� ������������ � ���� txt/output.txt.
	 */

	private final static String INPUT_FILE_ADDRESS = "input.txt";
	private final static String OUTPUT_FILE_ADDRESS = "output.txt";
	private final static String MESSAGE_IO_EXCEPTION = "Something wrong with input or output file!";

	int[] inputArr;
	ArrayList<Integer> outputArr;

	public static void main(String[] args) {

		Main obj = new Main();
		try {
			obj.createInputArr();
			obj.outputArr = obj.createOutputArr();
			obj.createOutputFile();
		} catch (IOException e) {
			System.out.println(MESSAGE_IO_EXCEPTION + " " + e);
		}

	}

	private void createInputArr() throws IOException {
		String[] input = readInputFile();
		inputArr = new int[Integer.valueOf(input[0])];
		inputArr = getInputArr(input[1]);
	}

	private String[] readInputFile() throws IOException {
		FileReader reader = new FileReader(INPUT_FILE_ADDRESS);
		Scanner sc = new Scanner(reader);
		int i = 0;
		String[] result = new String[2];

		while (sc.hasNextLine() && i < result.length) {
			result[i++] = sc.nextLine();
		}
		sc.close();
		reader.close();
		return result;
	}

	private int[] getInputArr(String input) {
		input = input.trim();
		String[] tempResult = input.split(" ");

		int[] result = new int[tempResult.length];

		for (int i = 0; i < tempResult.length; i++) {
			result[i] = Integer.valueOf(tempResult[i]);
		}
		return result;
	}

	private ArrayList<Integer> createOutputArr() {
		outputArr = new ArrayList<Integer>();
		int sumNegativeNumbers = 0;
		int sumPositiveNumbers = 0;
		ArrayList<Integer> negativeNumIndexes = new ArrayList<Integer>();
		ArrayList<Integer> positiveNumIndexes = new ArrayList<Integer>();
		for (int i = 0; i < inputArr.length; i++) {
			if (inputArr[i] > 0) {
				sumPositiveNumbers += inputArr[i];
				positiveNumIndexes.add(i + 1);
			} else {
				sumNegativeNumbers += inputArr[i];
				negativeNumIndexes.add(i + 1);
			}
		}
		if (sumPositiveNumbers >= Math.abs(sumNegativeNumbers)) {
			return positiveNumIndexes;
		} else {
			return negativeNumIndexes;
		}
	}

	private void createOutputFile() throws IOException {
		FileWriter writer = new FileWriter(OUTPUT_FILE_ADDRESS);
		writer.write(Integer.toString(outputArr.size()) + "\n");
		Iterator<Integer> iter = outputArr.iterator();
		while (iter.hasNext()) {
			writer.write(Integer.toString(iter.next()) + " ");
		}
		writer.close();
	}

}
