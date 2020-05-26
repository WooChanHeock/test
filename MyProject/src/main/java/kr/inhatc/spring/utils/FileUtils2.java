package kr.inhatc.spring.utils;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.inhatc.spring.member.dto.FileDto;

@Component
public class FileUtils2 {

	public List<FileDto> parseFileInfo(int memberIdx, MultipartHttpServletRequest multipartHttpServletRequest){
		
		if(ObjectUtils.isEmpty(multipartHttpServletRequest)) {
			return null;
		}
		
		List<FileDto> fileList = new ArrayList<FileDto>();
		
		// 파일이 업로드될 폴더 생성
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
		ZonedDateTime current = ZonedDateTime.now();
		String path = "images/" + current.format(format);
		
		File file = new File(path);
		if(file.exists() == false) {
			file.mkdir();
		}
		
		Iterator<String> iter = multipartHttpServletRequest.getFileNames();
		
		String originalFileExtension = null;
		while(iter.hasNext()){
			List<MultipartFile> list = multipartHttpServletRequest.getFiles(iter.next());
			
			for (MultipartFile multipartFile : list) {
				if(multipartFile.isEmpty() == false) {
					
					String contentType = multipartFile.getContentType();
					if(ObjectUtils.isEmpty(contentType)) {
						break;
					} else {
						if(contentType.contains("image/jpeg")) {
							originalFileExtension = ".jpg";
						}else if(contentType.contains("image/png")) {
						originalFileExtension = ".png";
						}else if(contentType.contains("image/gif")) {
						originalFileExtension = ".gif";
						}else {
						break;
						}
					}
					
					String newFileName = Long.toString(System.nanoTime()) + originalFileExtension;
					
					FileDto memberFile = new FileDto();
					memberFile.setMemberIdx(memberIdx);
					memberFile.setFileSize(multipartFile.getSize());
					memberFile.setOriginalFileName(multipartFile.getOriginalFilename());
					memberFile.setStoredFilePath(path + "/" + newFileName);
					fileList.add(memberFile);
					
					file = new File(path + "/" + newFileName);
					try {
						multipartFile.transferTo(file);
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		return fileList;
	}
}
