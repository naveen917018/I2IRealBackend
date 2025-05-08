package com.I2I.I2IBaceknd.Controller;


	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
	import org.springframework.web.multipart.MultipartFile;

import com.I2I.I2IBaceknd.Dao.NewsDto;
import com.I2I.I2IBaceknd.Entitiy.NewsEntity;
import com.I2I.I2IBaceknd.Service.NewsService;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

	@RestController
	@RequestMapping("/api/news")
	public class NewsMasterController {

	    @Autowired
	    private NewsService newsService;

	    // Save news data including the image as byte array
	    @PostMapping("/save")
	    public NewsEntity saveNews(
	            @RequestParam String header,
	            @RequestParam String description,
	            @RequestParam MultipartFile image) throws IOException {

	           byte[] imageBytes = image.getBytes();  // Convert image to byte array

	        NewsEntity news = new NewsEntity(header, description, imageBytes, true);
	        return newsService.saveNews(news);
	    }
	    @GetMapping("/findall")
	    public ResponseEntity<List<NewsDto>> getAllActiveNews() {
	        List<NewsEntity> newsList = newsService.getAllActiveNews();

	        List<NewsDto> response = newsList.stream().map(news -> {
	            NewsDto dto = new NewsDto();
	            dto.setId(news.getId());
	            dto.setHeader(news.getHeader());
	            dto.setDescription(news.getDescription());
	            dto.setImage(Base64.getEncoder().encodeToString(news.getImage()));
	            return dto;
	        }).collect(Collectors.toList());

	        return ResponseEntity.ok(response);
	    }
	    @GetMapping("/active/first")
	    public ResponseEntity<NewsDto> getFirstActiveNews() {
	        NewsDto news = newsService.getFirstActiveNews();
	        if (news != null) {
	            return ResponseEntity.ok(news);
	        } else {
	            return ResponseEntity.noContent().build(); // 204 No Content
	        }
	    }


	    // Get all saved news (just as an example)
//	    @GetMapping("/all")
//	    public List<NewsEntity> getAllNews() {
//	        return newsService.getAllNews();
//	    }
	
	    
}
