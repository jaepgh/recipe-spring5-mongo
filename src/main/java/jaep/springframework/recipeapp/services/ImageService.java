package jaep.springframework.recipeapp.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface ImageService {
    void saveImageFile(String recipeId, MultipartFile file);
    InputStream getImageById(String recipeId);
}
