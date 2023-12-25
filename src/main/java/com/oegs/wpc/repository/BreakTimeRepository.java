package com.oegs.wpc.repository;

import com.oegs.wpc.model.BreakTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BreakTimeRepository extends JpaRepository<BreakTime, UUID> {
}
