package com.example.MyBlog.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@NotNull
public enum Category {
    TECH,
    SPORTS,
    ENTERTAINMENT,
    TRAVEL,
    HEALTH;
}
