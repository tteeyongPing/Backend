package dgu.cse.newsee.repository;

import dgu.cse.newsee.app.dto.AlarmDto;
import dgu.cse.newsee.domain.entity.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Long> {

    List<Alarm> findByUserId(Long userId);
}
