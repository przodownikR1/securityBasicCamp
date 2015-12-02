package pl.java.scalatech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.java.scalatech.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

}
