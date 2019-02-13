package jaep.springframework.recipeapp.services;

import jaep.springframework.recipeapp.commands.RecipeCommand;
import jaep.springframework.recipeapp.domain.Recipe;
import jaep.springframework.recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.stream.IntStream;

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

            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()){
                byteObjects[i++] = b;
            }

            recipe.setImage(byteObjects);

            recipeRepository.save(recipe);

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public InputStream getImageById(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).get();

        if (recipe.getImage() == null){
            return null;
        }

        byte[] bytes = new byte[recipe.getImage().length];
        IntStream.range(0, recipe.getImage().length)
                .forEach(
                        i -> bytes[i] = recipe.getImage()[i]
                );

        InputStream is = new ByteArrayInputStream(bytes);

        return is;
    }


}
