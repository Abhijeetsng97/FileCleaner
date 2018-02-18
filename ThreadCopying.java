import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ThreadCopying extends Thread{
	final File file;
	final File destfolder;
	final File destfile;
	
	ThreadCopying(final File file, final File destfolder){
		this.file = file;
		this.destfolder = destfolder;
		this.destfile = new File(destfolder.toString()+"\\"+file.getName());
	}
	
	public void run() {
		/*
		 * Will copy file from source to destination
		 */
		try {
			Files.copy(file.toPath(), destfile.toPath());
			file.delete();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}