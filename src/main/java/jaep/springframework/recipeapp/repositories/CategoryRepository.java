package jaep.springframework.recipeapp.repositories;

import jaep.springframework.recipeapp.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
