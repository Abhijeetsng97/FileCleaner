import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Main {

	public static void main(String[] args) {
		
		Scanner reader = new Scanner(System.in);		
		String username = System.getProperty("user.name");
		String OS = System.getProperty("os.name");
		String in;
		List <File> userFolders = new ArrayList<File>();
		if(OS.startsWith("Window")) {
			String common = System.getenv("SystemDrive")+"\\Users\\"+username;
			String desktop = common+"\\Desktop";
			String documents = common+"\\Documents";
			String downloads = common+"\\Downloads";
			String music = common+"\\Music";
			String pictures = common+"\\Pictures";
			String videos = common+"\\Videos";

			userFolders.add(new File(desktop));
			userFolders.add(new File(documents));
			userFolders.add(new File(downloads));
			userFolders.add(new File(music));
			userFolders.add(new File(pictures));
			userFolders.add(new File(videos));
			List <File> drives = Arrays.asList(File.listRoots());
			Vector threads = new Vector();
			int i=0;
			/*
			 * Scanning for drives on window
			 */
			for (File f : drives) {
				if(f.exists()) {
					System.out.println("Do you want to get top 10 file size of "+f+" "+"(y\\n)");
					in = reader.next();
					if(in.contentEquals("y")) {
						ThreadScanning th = new ThreadScanning(f);
						th.start();
						threads.add(th);
						i++;
					}
				}
			}
			/*
			 * Scanning of user's personal folders
			 */
			for (File f : userFolders) {
				if(f.exists()) {
					System.out.println("Do you want to get top 10 file size of "+f+" "+"(y\\n)");
					in = reader.next();
					if(in.contentEquals("y")) {
						ThreadScanning th = new ThreadScanning(f);
						th.start();
						threads.add(th);
						i++;
					}
				}
			}
			/*
			 * Cleaning of users personal folder to document folder extension wise
			 */
			for (File f : userFolders) {
				if(f.exists()) {
					System.out.println("Do you want to clean "+f+" "+" to Documents folder");
					System.out.println("Enter y1 to clean files, y2 to clean files in sub-folder, n to skip");
					in = reader.next();
					if(in.contentEquals("y1")) {
						ThreadCleaning th = new ThreadCleaning(f, new File(documents), 1);
						th.start();
						threads.add(th);
						i++;
					}
					else if(in.contentEquals("y2")) {
						ThreadCleaning th = new ThreadCleaning(f, new File(documents), 2);
						th.start();
						threads.add(th);
						i++;
					}
				}
			}
			/*
			 * Checking if all thread finish their jobs
			 */
			for (int h=0;h<threads.size();h++){
				try {
					((Thread) threads.get(h)).join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("\n\n<--PROGRAMS ENDS -->");
		}
		else if(OS.startsWith("Linux")){
			String desktop = "/home/"+username+"/Desktop";
			String documents = "/home/"+username+"/Documents";
			String downloads = "/home/"+username+"/Downloads";
			String music = "/home/"+username+"/Music";
			String pictures = "/home/"+username+"/Pictures";
			String videos = "/home/"+username+"/Videos";

			userFolders.add(new File(desktop));
			userFolders.add(new File(documents));
			userFolders.add(new File(downloads));
			userFolders.add(new File(music));
			userFolders.add(new File(pictures));
			userFolders.add(new File(videos));
			List <File> drives = Arrays.asList(File.listRoots());
			Vector threads = new Vector();
			int i=0;
			/*
			 * Scanning root
			 */
			for (File f : drives) {
				System.out.println("Do you want to get top 10 file size of "+f+" "+"(y\\n)");
				in = reader.next();
				if(in.contentEquals("y")) {
					ThreadScanning th = new ThreadScanning(f);
					th.start();
					threads.add(th);
					i++;
				}
			}
			/*
			 * Scanning user files in linux
			 */
			for (File f : userFolders) {
				if(f.exists()) {
					System.out.println("Do you want to get top 10 file size of "+f+" "+"(y\\n)");
					in = reader.next();
					if(in.contentEquals("y")) {
						ThreadScanning th = new ThreadScanning(f);
						th.start();
						threads.add(th);
						i++;
					}
				}
			}
			/*
			 * Cleaning of files in linux
			 */
			for (File f : userFolders) {
				if(f.exists()) {
					System.out.println("Do you want to clean "+f+" "+" to Documents folder");
					System.out.println("Enter y1 to clean files, y2 to clean files in sub-folder, n to skip");
					in = reader.next();
					if(in.contentEquals("y1")) {
						ThreadCleaning th = new ThreadCleaning(f, new File(documents), 1);
						th.start();
						threads.add(th);
						i++;
					}
					else if(in.contentEquals("y2")) {
						ThreadCleaning th = new ThreadCleaning(f, new File(documents), 2);
						th.start();
						threads.add(th);
						i++;
					}
				}
			}
			/*
			 * Waiting for all threads to finish their task
			 */
			for (int h=0;h<threads.size();h++){
				try {
					((Thread) threads.get(h)).join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("\n\n<--PROGRAMS ENDS -->");
		}
		else{
			System.out.println("Platform not supported as of now :(");
		}			
	}
}
