package dgu.cse.newsee.service.news;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dgu.cse.newsee.domain.entity.News;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class NewsShortsServiceImpl implements NewsShortsService {

    // GPT
    String apiUrl = "https://api.openai.com/v1/chat/completions";
    @Value("${gpt.gpt_secret_key}")
    String secretKey;

    @Override
    public String getShorts(News news) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "Bearer " + secretKey);

        List<Map<String, String>> messages = new ArrayList<>();
        Map<String, String> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", "뉴스 본문을 한국어로 3줄 요약해줘. \"" + news.getContent() + "\"");
        messages.add(message);

        Map<String, Object> body = new HashMap<>();
        body.put("model", "gpt-3.5-turbo");
        body.put("messages", messages);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        try {
            // API 호출
            ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, requestEntity, String.class);

            // 응답 바디 가져오기
            String responseBody = response.getBody();

            // JSON 파싱을 위한 ObjectMapper 생성
            ObjectMapper objectMapper = new ObjectMapper();

            // 응답 JSON을 Map으로 변환
            Map<String, Object> responseMap = objectMapper.readValue(responseBody, new TypeReference<Map<String, Object>>() {});

            // 'choices' 배열에서 'message' 객체의 'content' 추출
            List<Map<String, Object>> choices = (List<Map<String, Object>>) responseMap.get("choices");
            Map<String, Object> firstChoice = choices.get(0);
            Map<String, Object> messageContent = (Map<String, Object>) firstChoice.get("message");
            String assistantResponse = (String) messageContent.get("content");

            // 결과 반환 (앞뒤 공백 제거)
            return assistantResponse.trim();

        } catch (HttpClientErrorException e) {
            // HTTP 오류 처리
            e.printStackTrace();
            return "API 호출 중 HTTP 오류가 발생했습니다: " + e.getStatusCode();
        } catch (JsonProcessingException e) {
            // JSON 처리 예외 처리
            e.printStackTrace();
            return "JSON 처리 중 오류가 발생했습니다.";
        } catch (Exception e) {
            // 기타 예외 처리
            e.printStackTrace();
            return "알 수 없는 오류가 발생했습니다.";
        }
    }
}
