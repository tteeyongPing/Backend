package dgu.cse.newsee.service.user;

import dgu.cse.newsee.domain.entity.User;
import dgu.cse.newsee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserAccountServiceImpl implements UserAccountService{

    private final UserRepository userRepository;

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
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
