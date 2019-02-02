//package com.spring.monitor.repository;
//
//import com.spring.monitor.entity.ClientEntity;
//import java.util.List;
//import java.util.Optional;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Component;
//
//@Component
//public interface JpaClientRepository extends JpaRepository<ClientEntity, String> {
//
//  List<ClientEntity> findByFirstName(String firstName);
//
//  List<ClientEntity> findByLastName(String lastName);
//
//  Optional<ClientEntity> findById(String id);
//
//  ClientEntity findByEmail(String email);
//}