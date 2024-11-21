package com.eshopweb.service.impl;

import com.eshopweb.settings.CaptchaSettings;
import com.eshopweb.utility.RecaptchaUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
@Service
public class RecaptchaService {

    private final RestTemplateBuilder restTemplateBuilder;
    private final CaptchaSettings captchaSettings;

    public String verifyRecaptcha(String ip, String recaptchaResponse) {

        Map<String, String> body = new HashMap<>();
        body.put("secret", captchaSettings.getSecret());
        body.put("response", recaptchaResponse);
        body.put("remoteip", ip);

        log.debug("Request body for recaptcha: {}", body);

        ResponseEntity<Map> recaptchaResponseEntity = restTemplateBuilder.build()
                        .postForEntity(captchaSettings.getUrl() + "?secret={secret}&response={response}&remoteip={remoteip}", body, Map.class, body);

        log.debug("Response from recaptcha: {}", recaptchaResponseEntity);
        final Map responseBody = recaptchaResponseEntity.getBody();

        boolean recaptchaSucess = (Boolean) responseBody.get("success");
        if (!recaptchaSucess) {
            return Stream.of(responseBody.get("error-codes"))
                    .map(Object::toString)
                    .map(RecaptchaUtil.RECAPTCHA_ERROR_CODE::get)
                    .collect(Collectors.joining(", "));
        } else {
            return StringUtils.EMPTY;
        }
    }

}
