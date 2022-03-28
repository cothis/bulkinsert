package com.example.bulkinsert.repository;

import com.example.bulkinsert.domain.Allotment;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@MybatisTest
@Slf4j
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AllotmentMapperTest {

    @Autowired
    private AllotmentMapper repository;

    private static final int INSERT_COUNT = 10000;
    long startTime;
    int chunkSize = 1000;

    @BeforeEach
    void before() {
        startTime = System.currentTimeMillis();
    }

    @AfterEach
    void after() {
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        log.info("elapsedTime: {}", elapsedTime);
    }


    @Test
    void create() {
        for (long i = 0; i < INSERT_COUNT; i++) {
            repository.create(new Allotment(i));
        }
    }

    @Test
    void createItems() {
        List<Allotment> allotments = IntStream.range(0, INSERT_COUNT)
                .mapToObj(i -> new Allotment((long) i))
                .collect(Collectors.toList());

        AtomicInteger counter = new AtomicInteger();

        Collection<List<Allotment>> chunkedList = allotments.stream()
                .collect(Collectors.groupingBy(item -> counter.getAndIncrement() / chunkSize))
                .values();

        int chunk = 0;
        for (List<Allotment> allotmentList : chunkedList) {
            repository.createAllotments(allotmentList);
            log.info("chunk : {}, listSize: {}", ++chunk, allotmentList.size());
        }

    }
}