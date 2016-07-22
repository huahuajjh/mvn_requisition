package com.tq.requisition.presentation.actions.share;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.apache.commons.io.FileUtils;

import sun.misc.BASE64Decoder;

import com.tq.requisition.infrastructure.utils.Formater.OperationResult;
import com.tq.requisition.presentation.actions.BaseAction;

@SuppressWarnings("serial")
public class FileUploade extends BaseAction {	
	
	private String fileType;
    private File file ; //具体上传文件的 引用 , 指向临时目录中的临时文件  
    private String fileFileName ;  // 上传文件的名字 ,FileName 固定的写法  
//    private String fileContentType ; //上传文件的类型， ContentType 固定的写法
    
    private String baseSFFile;//base64图片文件
	
	public String updateFile() throws IOException{
		String jsonState = "";
		String path = request().getRealPath("/uploadFile");
		path = path + ConstValue.getType(fileType);
		createDir(path);
		String name =UUID.randomUUID().toString();
		int dotIndex = fileFileName.lastIndexOf(".");
		if(dotIndex > -1){
			name =name + fileFileName.substring(dotIndex);
		}
		path = path + "/" +name;
		File tempFile = null;
		try {
			tempFile = new File(path);
			if(!tempFile.exists()){
				tempFile.createNewFile();
			}
			FileUtils.copyFile(file, tempFile);
			file.delete();
			String rFilePath =fileFileName + "/uploadFile" + ConstValue.getType(fileType) + "/"+name;
			jsonState = toForMaterJson(OperationResult.SUCCESS,"上传文件成功",rFilePath);
		} catch (Exception e) {
			jsonState = toForMaterJson(OperationResult.ERROR,"上传文件失败");
		}
		response().getWriter().write(jsonState);
		return null;
	}
	
	public String saveFile() throws IOException{
		String stateJson = "";
		if(baseSFFile == null || baseSFFile.equals("")){
			stateJson = toForMaterJson(OperationResult.ERROR, "提交信息由错误");
		} else {
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] bytes = decoder.decodeBuffer(baseSFFile);
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {// 调整异常数据
					bytes[i] += 256;
				}
			}
			String path = request().getRealPath("/uploadFile") + ConstValue.getType("EVIDENCE_FILE");
			createDir(path);
			String fileName = UUID.randomUUID().toString() + ".jpg";
			File file = new File(path + "/" +fileName);
			if(file.exists()){
				file.createNewFile();
			}
			OutputStream out = null;
			try {
				out = new FileOutputStream(file);
				out.write(bytes);
				out.flush();
			}finally{
				if(out!=null){
					out.close();
				}
			}
			String rFilePath = "uploadFile" + ConstValue.getType("EVIDENCE_FILE") +"/"+ fileName;
			stateJson = toForMaterJson(OperationResult.SUCCESS, "上传文件成功",rFilePath);
		}
		response().getWriter().write(stateJson);
		return null;
	}
	
	private void createDir(String path){
		File dir = new File(path);
		if(!dir.exists()){
			dir.mkdirs();
		}
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

//	public void setFileContentType(String fileContentType) {
//		this.fileContentType = fileContentType;
//	}

	public void setBaseSFFile(String baseSFFile) {
		this.baseSFFile = baseSFFile;
	}

	
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
}
