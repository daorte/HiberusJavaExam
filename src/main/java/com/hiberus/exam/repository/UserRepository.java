package com.hiberus.exam.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.hiberus.exam.entity.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>{

}
