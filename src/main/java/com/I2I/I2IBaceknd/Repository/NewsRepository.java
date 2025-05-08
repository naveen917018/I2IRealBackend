package com.I2I.I2IBaceknd.Repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.I2I.I2IBaceknd.Entitiy.NewsEntity;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
    // Custom query methods can go here if needed
	List<NewsEntity> findByIsActiveTrue();
	Optional<NewsEntity> findFirstByIsActiveTrueOrderByIdAsc();

	
}
