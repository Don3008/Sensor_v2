package siemens.sensor.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import siemens.sensor.domain.Message;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface MessageRepo extends CrudRepository<Message, Integer> {

}
