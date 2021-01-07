package com.jh.mall;

import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SearchTest {
    @Autowired
    RestHighLevelClient restHighLevelClient;
    @Test
    public void m1(){
        System.out.println("restHighLevelClient = " + restHighLevelClient);
    }
}
