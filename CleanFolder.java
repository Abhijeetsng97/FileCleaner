import java.io.File;
import java.util.Vector;

public class CleanFolder {
	
	Vector thread = new Vector();
	public void clean(File folder, File destfolder, int type) {
		String ext;
		try {
			for (final File file : folder.listFiles()) {
				if(file.isDirectory() && type == 2) {
					clean(file, destfolder, type);
				}
				if(file.isFile()) {
					ext = getFileExt(file);
					/*
					 * Excluding shortcuts and exe files
					 */
					if(ext != null && !ext.contains("exe") && !ext.contains("lnk") 
							&& !ext.contains("ini")) {
						File directory = new File(destfolder.toString()+"//"+ext);
						if(!dirPresent(directory)) 
							directory.mkdir();
						ThreadCopying tc = new ThreadCopying(file, directory);
						tc.start();
						thread.add(tc);
					}
				}
			}
		}catch(Exception e){}
		
		for (int h=0;h<thread.size();h++){
			try {
				((Thread) thread.get(h)).join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * Checking if directory is present or not
	 */
	private boolean dirPresent(File dir) {
		boolean res = false;
		if(dir.exists()) {
			res = true;
		}
		return res;
	}

	/*
	 * Getting file extension of file
	 */
	private String getFileExt(File file) {
	    String name = file.getName();
	    try {
	        return name.substring(name.lastIndexOf(".") + 1);
	    } catch (Exception e) {
	        return null;
	    }
	}

}
