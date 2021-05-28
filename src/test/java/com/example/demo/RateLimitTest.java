package com.example.demo;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Duration;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
public class RateLimitTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BucketService bucketService;

    @Value("${app.ratelimit}")
    private int RATE_LIMIT;

    @BeforeEach
    public void resetBucket(){
        bucketService.resetBucket();
    }

    @Test
    public void okRequest() throws Exception {

        this.mockMvc
                .perform(get("/hello/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello")));
    }

    @Test
    public void toManyRequest() throws Exception {
        for (int i = 0; i < RATE_LIMIT; i++) {
            this.mockMvc
                    .perform(get("/hello/"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("Hello")));
        }

        this.mockMvc
                .perform(get("/hello/"))
                .andDo(print())
                .andExpect(status().isTooManyRequests());
    }

}
