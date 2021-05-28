package com.example.demo;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@PropertySource("classpath:application.properties")
public class BucketService {

    private Bucket bucket;
    private int RATE_LIMIT;

    public BucketService(@Value("${app.ratelimit}") int rateLimit) {
        this.RATE_LIMIT = rateLimit;
        Refill refill = Refill.intervally(RATE_LIMIT, Duration.ofMinutes(1));
        Bandwidth limit = Bandwidth.classic(RATE_LIMIT, refill);
        this.bucket = Bucket4j.builder()
                .addLimit(limit)
                .build();
    }

    public Bucket resolveBucket() {
        return this.bucket;
    }

    public void resetBucket() {
        this.bucket.addTokens(10);
    }
}
