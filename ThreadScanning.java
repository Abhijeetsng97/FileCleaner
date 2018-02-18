import java.io.File;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ThreadScanning extends Thread{
		final File folder;
		Map<String, Double> fileMap = new HashMap<String, Double>();
		
		ThreadScanning(final File folder){
			this.folder = folder;
		}
		
		public void run() {
			/*
			 * Scanning the folder and getting top 10 files from that folder
			 */
			ScanFiles sc = new ScanFiles();
			sc.listFiles(folder, fileMap, null);
			if(fileMap.size()>0) {
				System.out.println("\n\nTop "+fileMap.size()+" Files in drive "+ folder);
				DecimalFormat numberFormat = new DecimalFormat("#.000");
				Set set = sc.sortMap(fileMap).entrySet();
			    Iterator itr=set.iterator();  
			    while(itr.hasNext()){  
			        Map.Entry entry=(Map.Entry)itr.next();  
			        System.out.println(entry.getKey()+" "+numberFormat.format(entry.getValue())+" MB");  
			    } 
			}
		}
	}