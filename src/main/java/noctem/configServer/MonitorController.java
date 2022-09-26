package noctem.configServer;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MonitorController {
    @PostMapping("/refreshAll")
    public String refreshAll(HttpServletRequest servletRequest) {
        StringBuffer url = servletRequest.getRequestURL();
        String uri = servletRequest.getRequestURI();
        String target = url.toString().replaceAll(uri, "/monitor");

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        LinkedMultiValueMap<String, String> param = new LinkedMultiValueMap<String, String>();
        param.add("path", "*");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(param, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(target, request, String.class);

        return "targetUrl " + target;
    }
}
