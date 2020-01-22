package ph.com.fss.controller.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import ph.com.fss.model.FileMeta;

@Controller
public class FileController {
	
	String user = "jlipayon";
	
	LinkedList<FileMeta> files = new LinkedList<FileMeta>();
	
	@RequestMapping(value="/upload", headers = "content-type=multipart/*", method = RequestMethod.POST)
	public @ResponseBody LinkedList<FileMeta> upload(@RequestParam(value="file") MultipartFile file, String uuid) {
		
		System.out.println("File Upload!"+ uuid);
		
		 FileMeta fileMeta = null;
		 fileMeta = new FileMeta();
		 fileMeta.setFileName(file.getOriginalFilename());
		 fileMeta.setFileSize(file.getSize()/1024+" Kb");
		 fileMeta.setFileType(file.getContentType());
		 
		 try {
			fileMeta.setBytes(file.getBytes());
			
			String folderName = user+"="+uuid;
			File dest = new File("D:/temp/files/"+folderName);
			dest.mkdir();
			FileCopyUtils.copy(file.getBytes(), new FileOutputStream("D:/temp/files/"+ folderName+"/"+file.getOriginalFilename()));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		 files.add(fileMeta);
		return files;
 
	}
	
	@RequestMapping(value = "/get/{value}", method = RequestMethod.GET)
	 public void get(HttpServletResponse response, @PathVariable String value){
		
		 FileMeta getFile = files.get(Integer.parseInt(value));
		 
		 try {
			 	response.setContentType(getFile.getFileType());
			 	response.setHeader("Content-disposition", "attachment; filename=\""+getFile.getFileName()+"\"");
		        FileCopyUtils.copy(getFile.getBytes(), response.getOutputStream());
		 }catch (IOException e) {
				e.printStackTrace();
		 }
	 }
	
	@RequestMapping(value = "/removeFile", method = RequestMethod.POST)
	@ResponseBody
	 public Boolean get(String fileName, String uuid){
		 
		String folderName = user+"="+uuid;
		File root = new File("D:/temp/files/"+folderName);
		
		System.out.println("Delete "+ root.getAbsolutePath()+"/"+fileName);
		
		Collection<File> files = FileUtils.listFiles(root, null, false);
		
        for (Iterator<File> iterator = files.iterator(); iterator.hasNext();) {
            File file = (File) iterator.next();
            if (file.getName().equals(fileName)){
                System.out.println(file.getAbsolutePath());
                file.delete();
                return true;
            }
        }
		return false;
	 }
}
