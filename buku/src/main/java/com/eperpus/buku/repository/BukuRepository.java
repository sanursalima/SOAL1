package com.eperpus.buku.repository;

import com.eperpus.buku.entity.Buku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BukuRepository extends JpaRepository<Buku, Long> {
   
}