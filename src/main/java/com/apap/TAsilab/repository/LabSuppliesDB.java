package com.apap.TAsilab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apap.TAsilab.model.LabSuppliesModel;

public interface LabSuppliesDB extends JpaRepository<LabSuppliesModel, Long> {

}