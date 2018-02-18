import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ScanFiles {
	
	public String listFiles(final File folder, Map<String, Double> fileMap, String minName) {
		double value, min; 
		String name = minName;
		if(name == null) {
			min = 10000;
		}
		else {
			min = fileMap.get(name);
		}
		int size = fileMap.size();
		try {
			for (final File file : folder.listFiles()) {
		        if (file.isDirectory()) {
		        	String temp = listFiles(file,fileMap,name);
		        	name = temp;
		        	if(name == null) {
		    			min = 10000;
		    		}
		    		else {
		    			min = fileMap.get(name);
		    		}
		    		size = fileMap.size();
		        } 
		        else {
		        	
		            value = (double) file.length()/1024/1024;
		            if(size < 10) {
		            	size++;
		            	fileMap.put(file.getName(), value);
		            	if(value < min) {
		            		min = value;
			            	name = file.getName();
		            	}
		            }
		            else {
		            	if(value > min) {
		            		fileMap.remove(name);	            		
		            		fileMap.put(file.getName(), value);
		            		double temp = 10000;
		            		for (Entry<String, Double> entry : fileMap.entrySet()){
		            		    if(entry.getValue() < temp) {
		            		    	temp = entry.getValue();
		            		    	min = entry.getValue();
		            		    	name = entry.getKey().toString();
		            		    }
		            		}
		            	}
		            }
		        }
		    }
		}
		catch(Exception e){
			
		}	    
		return name;
	}
	
	/*
	 * Sorting map based on values to display top 10 files according to size
	 */	
	public Map<String, Double> sortMap(Map<String, Double> unsortMap) {

        List<Map.Entry<String, Double>> list =
                new LinkedList<Map.Entry<String, Double>>(unsortMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> o1,
                               Map.Entry<String, Double> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        Map<String, Double> sortedMap = new LinkedHashMap<String, Double>();
        for (Map.Entry<String, Double> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
}
