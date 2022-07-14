package com.example.classreservation.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.classreservation.bean.BookBean;


public interface BookRepository extends JpaRepository<BookBean, Integer>{

}