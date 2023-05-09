package com.learning.domains.scheduler_jobs.job_repositories;

import com.learning.domains.scheduler_jobs.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobRepository extends JpaRepository<Jobs, Long> {
    @Query("SELECT j FROM Jobs j WHERE j.disabled = 'N'")
    List<Jobs> findAllActiveJobs();

}
