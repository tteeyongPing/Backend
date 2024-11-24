package dgu.cse.newsee.service.user;

import dgu.cse.newsee.domain.entity.User;

import java.util.Optional;

public interface UserAccountService {
    Optional<User> saveKakaoUser(String email);
    Optional<User> findKakaoUserByEmail(String email);
    Optional<User> findById(Long id);
    Long getUserIdFromToken(String token);
    String getActualTokenFromToken(String token);
    void updateNickname(Long userId, String newNickname);
    void deleteUser(Long userId);
    void logout(String token);
    String getNickname(Long userId);
}
