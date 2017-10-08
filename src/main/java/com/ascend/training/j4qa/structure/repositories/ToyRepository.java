package com.ascend.training.j4qa.structure.repositories;

import com.ascend.training.j4qa.structure.entities.Toy;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called toyRepository
 * CRUD refers Create, Read, Update, Delete
 */

public interface ToyRepository extends JpaRepository<Toy, Long>{
}
