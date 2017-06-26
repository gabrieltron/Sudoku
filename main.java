import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class main {

	static boolean result = true;
	static int[][] grid;
	static int totalThreads;
	
	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		grid = readFile();
		printTable();
		//Pega tamanho número de threads
		totalThreads = Integer.parseInt(args[0]);
		//Programa possui 27 etapas, mais que 27 threads é desnecessário
		if (totalThreads > 27) totalThreads = 27;
		//Cria vetor de threas e depois cria e inicializa
		Threads[] threads = new Threads[totalThreads];
		for (int i = 1; i <= totalThreads; i++) {
			threads[(i - 1)] = new Threads(i);
			threads[(i - 1)].start();
		}

		for (int i = 0; i < totalThreads; i++) {
			threads[i].join();
		}
		printResult();
	}

	static void testResult(int[] functions, int id) {
		int temp;
			for (temp = 0; temp < functions.length; temp++) {
				switch(functions[temp]) {
					 //Testa os 9 blocos
					case 1:
						testBlocks(0, 2, 0, 2, "1", id);
						break;
					case 2:
						testBlocks(3, 5, 0, 2, "2", id);
						break;
					case 3:
						testBlocks(6, 8, 0, 2, "3", id);
						break;
					case 4:
						testBlocks(0, 2, 3, 5, "4", id);
						break;
					case 5:
						testBlocks(3, 5, 3, 5, "5", id);
						break;
					case 6:
						testBlocks(6, 8, 3, 5, "6", id);
						break;
					case 7:
						testBlocks(0, 2, 6, 8, "7", id);
						break;
					case 8:
						testBlocks(3, 5, 6, 8, "8", id);
						break;
					case 9:
						testBlocks(6, 8, 6, 8, "9", id);
						break;
						//Testa as 9 linhas
					case 10:
						testRow(0, id);
						break;
					case 11:
						testRow(1, id);
						break;
					case 12:
						testRow(2, id);
						break;
					case 13:
						testRow(3, id);
						break;
					case 14:
						testRow(4, id);
						break;
					case 15:
						testRow(5, id);
						break;
					case 16:
						testRow(6, id);
						break;
					case 17:
						testRow(7, id);
						break;
					case 18:
						testRow(8, id);
						break;
						//Testa as 9 colunas
					case 19:
						testColumn(0, id);
						break;
					case 20:
						testColumn(1, id);
						break;
					case 21:
						testColumn(2, id);
						break;
					case 22:
						testColumn(3, id);
						break;
					case 23:
						testColumn(4, id);
						break;
					case 24:
						testColumn(5, id);
						break;
					case 25:
						testColumn(6, id);
						break;
					case 26:
						testColumn(7, id);
						break;
					case 27:
						testColumn(8, id);
						break;
					default:
						break;
				}
			}
	}

	static void testColumn(int column, int id) {
		int[] temp = new int[9];
		int index = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (grid[i][column] == temp[j]) {
					result = false;
					System.out.print("Thread " + id + ": erro na coluna " + (column + 1) + "\n");
				}
			}
			temp[index++] = grid[i][column];
		}	
	}

	static void testRow(int row, int id) {
		int[] temp = new int[9];
		int index = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (grid[row][i] == temp[j]) {
					result = false;
					System.out.print("Thread " + id + ": erro na linha " + (row + 1) + "\n");
				}
			}
			temp[index++] = grid[row][i];
		}
	}
	
	static void testBlocks(int sColumn, int eColumn, int sRow, int eRow, String bloco, int id) {
		int[] temp = new int[9];
		int k;
		int index = 0;
		for (int i = sRow; i <= eRow; i++ ) {
			for (int j = sColumn; j <= eColumn; j++) {
				for (k = 0; k < 9; k++) {
					if (grid[i][j] == temp[k]) {
						result = false;
						System.out.print("Thread " + id + ": erro na região " + j + "\n");
					}
				}
				temp[index++] = grid[i][j];
			}
		}
	}
	
	static void printTable() {
		System.out.print("A matriz inserida foi:\n");
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(" " + grid[i][j]);
			}
			System.out.print("\n");
		}
	}
	
	static void printResult() {
		if (result) {
			System.out.print("O resultado está correto!\n");
		}
	}
	
	static int[][] readFile() throws FileNotFoundException {
		//Abre o arquivo para leitura
		File file = new File("./src/input/input_grid_errado.txt");
		Scanner scan = new Scanner(file);
		// Declara grid
		int[][] grid = new int[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				grid[i][j] = scan.nextInt();
			}
		}
		scan.close();
		return grid;
	}

}
