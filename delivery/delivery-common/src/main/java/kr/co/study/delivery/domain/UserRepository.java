package kr.co.study.delivery.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    // findAll 했을 때 우리가 원하는 리스트 형태로 돌려주기 위해 써줌
    List<User> findAll();

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);
}
