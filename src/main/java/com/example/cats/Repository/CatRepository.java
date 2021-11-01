package com.example.cats.Repository;

import com.example.cats.Model.Cat;
import org.springframework.data.repository.CrudRepository;

public interface CatRepository extends CrudRepository<Cat, Long> {
}
