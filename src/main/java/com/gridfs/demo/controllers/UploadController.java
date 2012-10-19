package com.gridfs.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/upload")
public class UploadController {
	
	@Autowired
	GridFsTemplate gridFsTemplate;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getUploadForm(Model model) {
		model.addAttribute(new UploadItem());
		return "upload/uploadForm";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(UploadItem uploadItem, BindingResult result) throws Exception{
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.err.println("Error: " + error.getCode() + " - "
						+ error.getDefaultMessage());
			}
			return "upload/uploadForm";
		}

		gridFsTemplate.store(uploadItem.getFileData().getInputStream(), uploadItem.getFileData().getOriginalFilename());

		return "upload/uploadSuccess";
	}
}
