package com.chandlertu.minion;

import org.springframework.data.repository.CrudRepository;

public interface ReachResultRepository extends CrudRepository<ReachResult, Integer> {

  ReachResult findByHostAddress(String hostAddress);

}
