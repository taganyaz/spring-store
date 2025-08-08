package com.codewithedward.store.repositories;

import com.codewithedward.store.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Byte> {
}
