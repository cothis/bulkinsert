package com.example.bulkinsert.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
@Setter
public class Allotment {
    private final Long id;
    private double amount;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;
    private String creator;
    private String modifier;
}
