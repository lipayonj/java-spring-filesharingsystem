package ph.com.fss.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ph.com.fss.exception.LdapBatchUpdateFailed;
import ph.com.fss.service.FetchLdapEmployeeService;

@Component
public class FSSTaskScheduler {
	
	@Autowired
	private FetchLdapEmployeeService ldpaEmp;
		
	@Autowired
	private TaskExecutor taskExecutor;
	
	@Scheduled(cron="0 0 * * * FRI")
	@Async
	public void fetchLdapData(){
		try {
			ldpaEmp.updateEmployeeList();
		} catch (LdapBatchUpdateFailed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(ldpaEmp.getAllEmployee().toString());
		
	}
	
	@Scheduled(cron="0 0 */2 * * *")
	@Async
	public void cleanTempFiles() throws IOException{	
		
		System.out.println("Cleaning Temporary Files .... "); 
		
        File rootFolder = new File("D:/temp/files");
        File[] folders = rootFolder.listFiles((FileFilter) FileFilterUtils.directoryFileFilter());
        
        for (File file: folders) {
            System.out.println("File = " + file.getCanonicalPath());
            BasicFileAttributes attr = Files.readAttributes(Paths.get(file.getPath()), BasicFileAttributes.class);
            System.out.println("created last " + hoursDiff( new Date(attr.creationTime().toMillis()), new Date()) + " ago");
            if(hoursDiff( new Date(attr.creationTime().toMillis()), new Date()) > 2){
            	file.delete();
            }
        }
	}
	
	private final static long SECOND_MILLIS = 1000;
	private final static long MINUTE_MILLIS = SECOND_MILLIS*60;
	private final static long HOUR_MILLIS = MINUTE_MILLIS*60;
	
    private static int hoursDiff( Date earlierDate, Date laterDate )
    {
        if( earlierDate == null || laterDate == null ) return 0;
        return (int) ((laterDate.getTime()/HOUR_MILLIS) - (earlierDate.getTime()/HOUR_MILLIS));
    }
	
}
