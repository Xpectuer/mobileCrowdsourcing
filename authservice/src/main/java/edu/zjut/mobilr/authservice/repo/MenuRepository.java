package edu.zjut.mobilr.authservice.repo;

import edu.zjut.mobilr.authservice.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Long> {

}
