import java.io.File;
public class ThreadCleaning extends Thread{
	final File folder;
	final File destfolder;
	int type;
	
	ThreadCleaning(final File folder, final File destfolder, int type){
		this.folder = folder;
		this.type = type;
		this.destfolder = destfolder;
	}
	
	public void run() {
		CleanFolder cf = new CleanFolder();
		cf.clean(folder, destfolder, type);
	}
}