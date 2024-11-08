package dgu.cse.newsee.service.user;

import dgu.cse.newsee.domain.entity.User;

import java.util.Optional;

public interface UserAccountService {
    Optional<User> saveKakaoUser(String email);
    Optional<User> findByEmail(String email);
}
