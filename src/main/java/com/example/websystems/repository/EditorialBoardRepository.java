package com.example.websystems.repository;

import com.example.websystems.entity.EditorialBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditorialBoardRepository
        extends JpaRepository<EditorialBoard, Integer> {
}
