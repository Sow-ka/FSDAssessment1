/**
 * 
 */
package lokedmeapp.sk.com;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author Sowkarthika R
 *
 */
public class LockedMe {

	private static final String maindirpath = "C:\\Users\\raguns\\Desktop\\Sample";

	public static void main(String[] args) {
		try {

			System.out.println("***********Welcome to Company Lockers Pvt. Ltd***********\n");
			System.out.println("My First Project: Lockedme.com\n");
			System.out.println("Developer Name: Sowkarthika.R\n");
			System.out.println("Root Folder: \""+maindirpath+"\"" );
			Scanner scanner = new Scanner( System.in );
			crudFilesForUser(scanner);
		}
		catch(Exception e)
		{
			System.out.println("Some issue in procession the app..Please try again");
		}

	}

	private static void crudFilesForUser(Scanner scanner) {
		System.out.println("\nEnter the options \n" + "1.List down the files\n" + "2.Add/Delete & search for a file \n"
				+ "3.Close the App \n");
		String opt = scanner.nextLine();
		try {
			switch (Integer.parseInt(opt)) {
			case 1:
				// listFilesofDir();
				ListFiles();
			case 2:
				System.out.println(
						"\n Choose option to  \n" + "1.Add a new File \n" + "2.Delete a file \n" + "3.Search for a file \n");
				String opt1 = scanner.nextLine();
				int value=Integer.parseInt(opt1);
				switch (value) {
				case 1:
					System.out.println("Please enter the filename with extension to Add \n");
					String filename = scanner.nextLine();
					AddFiletoDir(filename);
					continueApp(scanner);
					break;
				case 2:
					System.out.println("please enter the exact filename to Delete \n");
					String filenameDel = scanner.nextLine();
					DeleteFileFromDir(filenameDel);
					continueApp(scanner);
					break;
				case 3:
					System.out.println("please enter the filename to Search\n");
					String filenameSearch = scanner.nextLine();
					searchFile(filenameSearch);
					continueApp(scanner);
					break;
				default:
					System.err.println("Please enter correct value..");
					continueApp(scanner);
					break;
				}
				break;
			case 3:
				System.out.println("Thank you !!");
				break;
			default:
				System.err.println("Please enter correct value..");
				continueApp(scanner);

			}
		}
		catch(Exception e)
		{
			System.out.println("Some issue in procession the app..Please try again\n");
			System.err.println("Please enter correct value..\n");
			continueApp(scanner);
		}

	}



	/**
	 * @param scanner
	 */
	private static void continueApp(Scanner scanner) {
		System.out.println("Do you want to close the app y/n");
		String opt3 = scanner.nextLine();
		if(opt3.equalsIgnoreCase("n"))
		{
			crudFilesForUser(scanner);
		}
		if(opt3.equalsIgnoreCase("y"))
		{
			System.out.println("Closing the app.. Thank you !!");
		}
	}

	//CRUD operations
	private static void DeleteFileFromDir(String fileName) { // TODO
		try {
			File tagFile = new File(maindirpath, fileName); // file to be delete
			if (tagFile.delete()) // returns Boolean value
			{
				System.out.println(tagFile.getName() + " deleted successfully !!");
			} else {
				System.out.println("File deletion failed");
			}
		} catch (Exception e) {
			System.out.println("File deletion failed");
		}
	}

	private static  void AddFiletoDir(String fileName) {

		File tagFile = new File(maindirpath, fileName);
		try {
			if (!tagFile.exists()) {
				tagFile.createNewFile();
				System.out.println("File added successfully !!!");
			} else {
				System.out.println("Filename already exist!!");
			}
		}

		catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("could not create a file.. please provide a valid name");
		}
	}

	private static void searchFile(String fileName)
	{
		File directory = new File(maindirpath);
		String[] flist = directory.list();
		int flag = 0;
		if (flist == null) {
			System.out.println("Empty directory.");
		}
		else {

			// Linear search in the array
			for (int i = 0; i < flist.length; i++) {
				String filename = flist[i];
				if (filename.equalsIgnoreCase(fileName)) {
					System.out.println(filename + " file found");
					flag = 1;
				}
			}
		}

		if (flag == 0) {
			System.out.println("File Not Found");
		}
	}

	/**
	 * 
	 */
	private static  void listFilesofDir() {


		// File object
		File maindir = new File(maindirpath);

		if(maindir.exists() && maindir.isDirectory())
		{
			// array for files and sub-;directories 
			// of directory pointed by maindir
			File arr[] = maindir.listFiles();

			System.out.println("**********************************************");
			System.out.println("Files from the user/current directory : " + maindir);
			System.out.println("**********************************************");

			// Calling recursive method
			RecursivePrint(arr,0,0); 
		} 
		else
		{
			System.out.println("Give path is not a valid Diractory");
		}
	}
	private static void RecursivePrint(File[] arr,int index,int level) 
	{
		// terminate condition
		if(index == arr.length)
			return;

		// tabs for internal levels
		for (int i = 0; i < level; i++)
			System.out.print("\t");

		// for files
		if(arr[index].isFile())
			System.out.println(arr[index].getName());

		// for sub-directories
		else if(arr[index].isDirectory())
		{
			System.out.println("[" + arr[index].getName() + "]");

			// recursion for sub-directories
			RecursivePrint(arr[index].listFiles(), 0, level + 1);
		}

		// recursion for main directory
		RecursivePrint(arr,++index, level);
	}

	private static void ListFiles()
	{
		List<Path> x = null;
		try {
			x = Files.list(Paths.get(maindirpath))
					.filter(p -> Files.exists(p))
					.map(s -> s.getFileName())
					.sorted()
					.collect(Collectors.toList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("The files are\n");
		x.forEach(System.out::println);
	}

}
