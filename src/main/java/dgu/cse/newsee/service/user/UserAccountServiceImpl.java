package dgu.cse.newsee.service.user;

import dgu.cse.newsee.domain.entity.User;
import dgu.cse.newsee.apiPayload.exception.UserException;
import dgu.cse.newsee.jwt.JWTUtil;
import dgu.cse.newsee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Transactional
public class UserAccountServiceImpl implements UserAccountService{

    private final UserRepository userRepository;
    @Autowired
    private final JWTUtil jwtUtil;
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public Optional<User> saveKakaoUser(String email) {
        User newUser = User.builder()
                .email(email)
                .name(email)
                .build();
        Optional<User> user = Optional.of(userRepository.save(newUser));
        return user;
    }

    @Override
    public Optional<User> findKakaoUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) { throw new UserException.UserNonExistsException("존재하지 않는 회원입니다."); }
        return user;
    }

    @Override
    public Long getUserIdFromToken(String token) {
        if (token == null || !token.startsWith("Bearer ")) throw new UserException.InvalidTokenException("유효하지 않은 토큰입니다.");

        String actualToken = token.replace("Bearer ", "");
        if (redisTemplate.hasKey(actualToken)) { throw new UserException.InvalidTokenException("로그아웃된 토큰입니다."); }
        if (jwtUtil.isExpired(actualToken)) { throw new UserException.ExpiredTokenException("만료된 토큰입니다."); }

        return jwtUtil.getUserId(actualToken);
    }

    @Override
    public String getActualTokenFromToken(String token) {
        if (token == null || !token.startsWith("Bearer ")) throw new UserException.InvalidTokenException("유효하지 않은 토큰입니다.");

        String actualToken = token.replace("Bearer ", "");
        if (redisTemplate.hasKey(actualToken)) { throw new UserException.InvalidTokenException("로그아웃된 토큰입니다."); }
        if (jwtUtil.isExpired(actualToken)) { throw new UserException.ExpiredTokenException("만료된 토큰입니다."); }

        return actualToken;
    }

    @Override
    public void updateNickname(Long userId, String newNickname) {
        if (userRepository.existsByName(newNickname)) { throw new UserException.NicknameAlreadyExistsException("이미 존재하는 닉네임입니다."); }
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) { throw new UserException.UserNonExistsException("존재하지 않는 회원입니다."); }

        User updateUser = user.get();
        updateUser.setNickname(newNickname);
        userRepository.save(updateUser);
    }

    @Override
    public void logout(String token) {
        Date expirationDate = jwtUtil.getExpirationDate(token);
        long expirationInMillis = expirationDate.getTime() - System.currentTimeMillis();

        redisTemplate.opsForValue().set(token, "logout", expirationInMillis, TimeUnit.MILLISECONDS);
    }

    @Override
    public String getNickname(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) { throw new UserException.UserNonExistsException("존재하지 않는 회원입니다."); }
        return user.get().getNickname();
    }

    @Override
    public void deleteUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) { throw new UserException.UserNonExistsException("존재하지 않는 회원입니다."); }

        userRepository.deleteById(userId);
    }

}
