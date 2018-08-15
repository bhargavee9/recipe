package com.bhargavee.recipe.repository;

import com.bhargavee.recipe.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public interface CategoryRepository extends CrudRepository<Category,Long> {
    java.util.Optional<Category> findByDescription(String description);

}
