package com.I2I.I2IBaceknd.Service;


import com.I2I.I2IBaceknd.Dao.NewsDto;
import com.I2I.I2IBaceknd.Entitiy.NewsEntity;
import com.I2I.I2IBaceknd.Repository.NewsRepository;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;
    // Save news data with isActive as true by default
    @Transactional
    public NewsEntity saveNews(NewsEntity news) {
        // Set isActive to true for new data and false for others
        if (news.getId() == null) {
            news.setActive(true);  // Default isActive true when new
        } else {
            news.setActive(false);  // Set isActive to false for all other data
        }
        return newsRepository.save(news);
    }
    @Transactional(readOnly = true) // REQUIRED to avoid auto-commit mode
    public List<NewsEntity> getAllActiveNews() {
        return newsRepository.findAll();
    }
    public NewsDto getFirstActiveNews() {
        return newsRepository.findFirstByIsActiveTrueOrderByIdAsc()
            .map(news -> {
                NewsDto dto = new NewsDto();
                dto.setHeader(news.getHeader());
                dto.setDescription(news.getDescription());
                dto.setImage(Base64.getEncoder().encodeToString(news.getImage()));
                return dto;
            })
            .orElse(null);
    }

}
