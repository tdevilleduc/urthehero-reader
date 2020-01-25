package com.tdevilleduc.urthehero.core.dao;

import com.tdevilleduc.urthehero.core.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageDao extends JpaRepository<Page, Integer> {

    Page findById(int id);
}
