package org.sid.dao;

import java.util.List;

import org.sid.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<Client, Long> {
	
	@Query("select c from Client c where c.nom = :x")
	public List<Client> findByKeyword(@Param(value="x")String mc);
	
}
