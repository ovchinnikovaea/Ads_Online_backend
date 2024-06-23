package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.Image;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}
