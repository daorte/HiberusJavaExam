package com.hiberus.exam.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.hiberus.exam.entity.SocialNetwork;

@Repository
public interface SocialNetworkRepository extends PagingAndSortingRepository<SocialNetwork, Long>{

}

