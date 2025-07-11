package com.urlshortener.repository;

import com.urlshortener.model.SubscriptionFee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionFeeRepository extends JpaRepository<SubscriptionFee, Long> {}
