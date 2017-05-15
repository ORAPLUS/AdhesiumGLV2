package org.sid.dao;

import org.sid.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query("select count(u)>0 from User u where lower(u.email) like lower(:x)")
	public boolean isExisteEmail(@Param("x") String email);
}
