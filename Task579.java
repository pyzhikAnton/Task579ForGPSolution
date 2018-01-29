import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Task579 {
	
	/**
	 * Программа для нахождения подпоследовательности чисел из входной последовательности,
	 * для которых модуль суммы наибольший.
	 * Программа сама генерирует набор чисел (в диапозоне [-10000;10000]
	 * в количестве n (1<=n<=10000) и записывает их в файл txt/input.txt.
	 * Результат в виде двух строк - первая строка количество элементов в
	 * полученной подпоследовательности, вторая - номера элементов (начиная с 1!).
	 * Результат записывается в файл txt/output.txt.
	 */

	private final static String INPUT_FILE_ADDRESS = "txt/input.txt";
	private final static String OUTPUT_FILE_ADDRESS = "txt/output.txt";
	private final static String MESSAGE_IO_EXCEPTION = "Something wrong with input or output file!";

	int[] inputArr;
	ArrayList<Integer> outputArr;

	private Task579() {
		try {
			createRandomInputFile();
		} catch (IOException e) {
			System.out.println(MESSAGE_IO_EXCEPTION + " " + e);
		}
		outputArr = new ArrayList<Integer>();
	}

	private void createRandomInputFile() throws IOException {
		int n = (int) (Math.random() * 10000 + 1);
		FileWriter writer = new FileWriter(INPUT_FILE_ADDRESS);
		writer.write(Integer.toString(n) + "\n");
		for (int i = 0; i <= n; i++) {
			writer.write(Integer.toString((int) (Math.random() * 20001 - 10000)) + " ");
		}
		writer.close();
	}

	public static void main(String[] args) {

		Task579 obj = new Task579();
		try {
			obj.createInputArr();
			obj.outputArr = obj.getOutputArr();
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

	private ArrayList<Integer> getOutputArr() {
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
