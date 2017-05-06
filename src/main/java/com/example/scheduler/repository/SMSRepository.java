package com.example.scheduler.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.scheduler.domain.SMS;

@Repository
public interface SMSRepository extends CrudRepository<SMS, Long> {

}
