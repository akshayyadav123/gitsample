package com.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.app.model.Document;
import com.app.service.IDocumentService;

@Controller
public class DocumentController {
	@Autowired
	private IDocumentService service;
	@RequestMapping("/showDocs")
	public String showDoc(ModelMap map){
		List<Object[]> list=service.getAllDocumentByIdAndName();
		map.addAttribute("docList",list);
		return "Document";


	}

	/*
	 * save(upload) the Document
	 */
	@RequestMapping(value="/saveDocument",method=RequestMethod.POST)
	public String UploadDoc(
			@RequestParam("fileId")int file,
			@RequestParam("fileOb")CommonsMultipartFile cmf
			){
		if (cmf!=null) {
			Document doc=new Document();
			doc.setFileId(file);
			doc.setFileName(cmf.getOriginalFilename());
			doc.setFileData(cmf.getBytes());

			service.saveDocument(doc);
		}

		return "Document";


	}
	
	@RequestMapping("/doDownload")
	public void downloadDocument(@RequestParam("fileId")int fid,HttpServletResponse resp){
		 Document doc=service.getDocById(fid);
		resp.addHeader("Content-Disposition", "attachment:fileName="+doc.getFileName());
		try {
			FileCopyUtils.copy(doc.getFileData(), resp.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}
