package dgu.cse.newsee.app.controller;


import dgu.cse.newsee.domain.enums.Category;
import dgu.cse.newsee.domain.entity.User;
import dgu.cse.newsee.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import jakarta.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.ActiveProfiles;
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class CategoryControllerTest {
    // 테스트 환경에서만 적용할 속성 재정의
    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", () -> "jdbc:mysql://localhost:3306/newsee");
        registry.add("spring.datasource.username", () -> System.getenv("LOCAL_USERNAME"));
        registry.add("spring.datasource.password", () -> System.getenv("LOCAL_PASSWORD"));
        registry.add("spring.datasource.driver-class-name", () -> "com.mysql.cj.jdbc.Driver");
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop"); // 테스트 시 테이블 생성 후 삭제
    }
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    private Long userId;

    @BeforeEach
    void setUp() {
        // 테스트용 사용자 생성
        User user = User.builder()
                .name("Test User")
                .email("test@example.com")
                .phone("123-456-7890")
                .build();
        userRepository.save(user);
        userId = user.getId();
    }
    @Test
    void 관심분야_추가하기_테스트() throws Exception {
        mockMvc.perform(post("/api/category/add")
                        .param("userId", String.valueOf(userId))
                        .param("interestCategory", Category.정치.name())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test User"))
                .andExpect(jsonPath("$.interestCategories[*].interestCategory").value("정치"));
    }
    @Test
    void 관심분야_가져오기_테스트() throws Exception {
        mockMvc.perform(get("/api/category/my")
                        .param("userId", String.valueOf(userId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }


}
