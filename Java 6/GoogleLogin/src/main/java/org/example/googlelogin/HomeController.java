package org.example.googlelogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.Period;

@Controller
public class HomeController {

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper mapper = new ObjectMapper();

    @GetMapping("/profile")
    public String profile(OAuth2AuthenticationToken authentication, Model model) throws Exception {
        OAuth2User principal = authentication.getPrincipal();
        // Thông tin cơ bản từ OIDC
        String name = principal.getAttribute("name");
        String email = principal.getAttribute("email");

        // Lấy access token của client đã ủy quyền
        OAuth2AuthorizedClient client =
                authorizedClientService.loadAuthorizedClient(
                        authentication.getAuthorizedClientRegistrationId(),
                        authentication.getName());

        if (client == null || client.getAccessToken() == null) {
            // không có token — hiển thị những gì có (name/email)
            model.addAttribute("name", name);
            model.addAttribute("email", email);
            model.addAttribute("age", "Không có quyền truy cập birthday");
            return "profile";
        }

        String accessToken = client.getAccessToken().getTokenValue();

        // Gọi People API để lấy birthdays
        String url = "API NIGGER";
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> resp = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        JsonNode root = mapper.readTree(resp.getBody());

        // Parse name/email nếu muốn lấy từ People API
        // Lấy birthdays node
        String ageText = "Không có dữ liệu ngày sinh";
        if (root.has("birthdays")) {
            JsonNode birthdays = root.get("birthdays");
            // birthdays là mảng; tìm entry có 'date' chứa year/month/day
            for (JsonNode b : birthdays) {
                if (b.has("date")) {
                    JsonNode date = b.get("date");
                    if (date.has("year") && date.has("month") && date.has("day")) {
                        int y = date.get("year").asInt();
                        int m = date.get("month").asInt();
                        int d = date.get("day").asInt();
                        LocalDate dob = LocalDate.of(y, m, d);
                        int age = Period.between(dob, LocalDate.now()).getYears();
                        ageText = String.valueOf(age);
                        break;
                    }
                }
            }
        }

        model.addAttribute("name", name);
        model.addAttribute("email", email);
        model.addAttribute("age", ageText);
        return "profile";
    }
}
