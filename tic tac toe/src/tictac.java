import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class tictac {

	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

	public static void main(String[] args) {
		char[][] Board = { { ' ', '|', ' ', '|', ' ' }, { '-', '+', '-', '+', '-' }, { ' ', '|', ' ', '|', ' ' },
				{ '-', '+', '-', '+', '-' }, { ' ', '|', ' ', '|', ' ' } };

		printGame(Board);
		while (true) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter your positon (1--9):");
			int playerPos = scan.nextInt();
			while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
				System.out.println("Position taken! Enter a correct Position");
				playerPos = scan.nextInt();
			}

			placePos(Board, playerPos, "player");
			
			String result = checkWinner();
			if (result.length() > 0) {
				printGame(Board);
				System.out.println(result);
				break;
			}

			Random rand = new Random();
			int cpuPos = rand.nextInt(9) + 1;
			while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
				System.out.println("Position taken! Enter a correct Position");
				cpuPos = rand.nextInt(9) + 1;
			}

			placePos(Board, cpuPos, "cpu");

			printGame(Board);

			result = checkWinner();
			if (result.length() > 0) {
				printGame(Board);
				System.out.println(result);
				break;
			}

		}
	}

	public static void printGame(char[][] Board) {
		for (char[] row : Board) {
			for (char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}

	public static void placePos(char[][] Board, int position, String user) {

		char symbol = 'X';
		if (user.equals("player")) {
			symbol = 'X';
			playerPositions.add(position);
		} else if (user.equals("cpu")) {
			symbol = 'O';
			cpuPositions.add(position);
		}

		switch (position) {
		case 1:
			Board[0][0] = symbol;
			break;
		case 2:
			Board[0][2] = symbol;
			break;
		case 3:
			Board[0][4] = symbol;
			break;
		case 4:
			Board[2][0] = symbol;
			break;
		case 5:
			Board[2][2] = symbol;
			break;
		case 6:
			Board[2][4] = symbol;
			break;
		case 7:
			Board[4][0] = symbol;
			break;
		case 8:
			Board[4][2] = symbol;
			break;
		case 9:
			Board[4][4] = symbol;
			break;
		}

	}

	public static String checkWinner() {
		List topRow = Arrays.asList(1, 2, 3);
		List midRow = Arrays.asList(4, 5, 6);
		List botRow = Arrays.asList(7, 8, 9);
		List leftCol = Arrays.asList(1, 4, 7);
		List midCol = Arrays.asList(2, 5, 8);
		List rightCol = Arrays.asList(3, 6, 9);
		List cross1 = Arrays.asList(1, 5, 9);
		List cross2 = Arrays.asList(7, 5, 3);

		List<List> winning = new ArrayList<List>();
		winning.add(topRow);
		winning.add(midRow);
		winning.add(botRow);
		winning.add(leftCol);
		winning.add(midCol);
		winning.add(rightCol);
		winning.add(cross1);
		winning.add(cross2);

		for (List l : winning) {
			if (playerPositions.containsAll(l)) {
				return "You Won";
			} else if (cpuPositions.containsAll(l)) {
				return "Cpu Wins";
			} else if (playerPositions.size() + cpuPositions.size() == 9) {
				return "Draw";
			}
		}

		return "";

	}
}
