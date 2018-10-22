package com.chandlertu.minion;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ReachResultRepository extends CrudRepository<ReachResult, Integer> {

  ReachResult findByHostAddress(String hostAddress);

  List<ReachResult> findByIsReachable(boolean isReachable);

}
