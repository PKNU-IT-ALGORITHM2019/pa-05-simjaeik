import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static BinaryTree<Word> mainTree = new BinaryTree<Word> ();

	public static Scanner kb = new Scanner(System.in);
	
	public static void main(String[] args) {

		try {
			handleRead();
			command();
		} catch (FileNotFoundException e) {
			System.out.println("Errors detacted.");
			e.printStackTrace();
		}
	}

	private static void command() throws FileNotFoundException {

		while(true) {
			System.out.print("$ ");
			String key = kb.nextLine();

			if(key.equals("size")){
				System.out.println("Size is "+ ( BinaryTree.size - 1 ) );
			}
			else if(key.split(" ")[0].equals("find")){
				String find = key.split(" ", 2)[1];
				handleFind(find);
			}
			else if(key.equals("add")){
				handleAdd();
			}
			else if(key.split(" ")[0].equals("delete")){
				String find = key.split(" ")[1];
				
				if (handleDelete(find))
					System.out.println("Deleted successfully.");
				else
					System.out.println("Not Found");
			}
			else if(key.split(" ")[0].equals("deleteall")){
				String find = key.split(" ")[1];
				handleDeleteAll(find);
			}
			else if(key.equals("exit")){
				break;
			}
		}
		kb.close();
	}

	private static void handleDeleteAll(String read) throws FileNotFoundException {
		Scanner file = new Scanner(new File(read));
		int dSize = 0;
		
		while(file.hasNextLine()) {
			handleDelete(file.nextLine());
			dSize++;
		}

		file.close();
		
		System.out.println(dSize + " words were deleted successfully.");
	}

	private static boolean handleDelete(String find) {
		if(mainTree.searchTree(mainTree.root, find) == null) 
			return false;
		
		mainTree.deleteTree(mainTree, mainTree.searchTree(mainTree.root, find));
		return true;
	}

	private static void handleAdd( ) {
		Word buf = new Word( makeLine() );
		BinaryTree <Word> tmp = new BinaryTree<Word>(buf);

		mainTree.insertTree(mainTree, tmp.root);
	}

	private static String makeLine() {

		System.out.print("word : ");
		String tmpWord = kb.nextLine();

		System.out.print("class : ");					/// 엔터일때 고치기, 다 못읽는거 고치기
		String tmpClass = kb.nextLine();
		
		System.out.print("meaning : ");
		String tmpMeaning = kb.nextLine();
		
		return tmpWord + " (" + tmpClass + ") " + tmpMeaning;
	}

	private static void handleFind(String find) {
		if(mainTree.searchTree(mainTree.root, find) == null) {
			System.out.println("There is no ' " + find + " '");
			return;
		}

		System.out.println( mainTree.searchTree(mainTree.root, find).data.toString() );
	}

	private static void handleRead() throws FileNotFoundException {
		Scanner fileReader = new Scanner(new File("shuffled_dict.txt"));

		while( fileReader.hasNextLine() ) {
			Word buf = new Word( fileReader.nextLine() );
			BinaryTree <Word> tmp = new BinaryTree<Word>(buf);

			mainTree.insertTree(mainTree, tmp.root);
		}
		
		fileReader.close();
	}

}