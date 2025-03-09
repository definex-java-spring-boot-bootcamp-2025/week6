package dev.patika.definexjavaspringbootbootcamp2025.week6.introductiontointegrationtesting.repositories;

import dev.patika.definexjavaspringbootbootcamp2025.week6.introductiontointegrationtesting.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
}