package com.glearning.TickettrackerApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.glearning.TickettrackerApplication.model.Ticket;
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

	
		//Iterable<Ticket> findTicketsByTitle(String title);
	 @Query(value = "select * from ticket t where t.title like %:title%", nativeQuery = true)
	 List<Ticket> findTicketsByTitle(@Param("title") String title);
	}
		
	


