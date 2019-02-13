package jaep.springframework.recipeapp.services;

import jaep.springframework.recipeapp.domain.Recipe;
import jaep.springframework.recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(Long recipeId, MultipartFile file) {
        try{
            Recipe recipe = recipeRepository.findById(recipeId).get();

            Byte[] bytes = new Byte[file.getBytes().length];

            for (int i = 0; i < file.getBytes().length; i++) {
                bytes[i] = file.getBytes()[i];
            }

            recipe.setImage(bytes);

            recipeRepository.save(recipe);

        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
