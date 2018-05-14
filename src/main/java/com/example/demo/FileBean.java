package com.example.demo;

import org.springframework.web.multipart.MultipartFile;

public class FileBean {

	private String attach_path;
	private MultipartFile upload;
	private String filename;
	private String CKEditorFuncNum;
	               
	public String getAttach_path() {
		return attach_path;
	}
	public void setAttach_path(String attach_path) {
		this.attach_path = attach_path;
	}
	public MultipartFile getUpload() {
		return upload;
	}
	public void setUpload(MultipartFile upload) {
		this.upload = upload;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getCKEditorFuncNum() {
		return CKEditorFuncNum;
	}
	public void setCKEditorFuncNum(String cKEditorFuncNum) {
		CKEditorFuncNum = cKEditorFuncNum;
	}
	@Override
	public String toString() {
		return "FileBean [attach_path=" + attach_path + ", upload=" + upload + ", filename=" + filename
				+ ", CKEditorFuncNum=" + CKEditorFuncNum + "]";
	}
	
	
}
