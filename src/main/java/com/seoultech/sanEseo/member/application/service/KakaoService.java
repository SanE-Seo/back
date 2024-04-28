package com.seoultech.sanEseo.member.application.service;

import com.seoultech.sanEseo.global.exception.BusinessException;
import com.seoultech.sanEseo.global.exception.ErrorType;
import com.seoultech.sanEseo.member.application.port.in.command.OAuthRegisterCommand;
import com.seoultech.sanEseo.member.application.port.out.MemberPort;
import com.seoultech.sanEseo.member.domain.AccessRefreshToken;
import com.seoultech.sanEseo.member.domain.Member;
import com.seoultech.sanEseo.member.domain.Provider;
import com.seoultech.sanEseo.member.exception.OAuthException;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.springframework.http.*;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class KakaoService {
    private final InMemoryClientRegistrationRepository inMemoryClientRegistrationRepository;
    private final AuthService authService;
    private final MemberService memberService;
    private final MemberPort memberPort;
    private final TokenService tokenService;

    public AccessRefreshToken login(final String code) {
        ClientRegistration provider =
                inMemoryClientRegistrationRepository.findByRegistrationId("kakao");

        AccessRefreshToken kakaoToken = getKakaoToken(provider, code);

        String email = getKakaoEmail(provider, kakaoToken.getAccessToken());

        Member member = null;
        try {
            member = memberPort.loadByEmail(email);
        } catch (IllegalArgumentException e) {
            authService.register(OAuthRegisterCommand.builder()
                    .provider(Provider.KAKAO)
                    .email(email)
                    .name(memberService.generateName())
                    .build());
            member = memberPort.loadByEmail(email);
        }

        String accessToken = tokenService.createAccessToken(member);
        String refreshToken = tokenService.createRefreshToken(member);
        return new AccessRefreshToken(accessToken,refreshToken);
    }

    private AccessRefreshToken getKakaoToken(ClientRegistration provider, String code) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity httpEntity = new HttpEntity(tokenRequest(provider, code), headers);
            ResponseEntity<String> response = restTemplate.exchange(
                    provider.getProviderDetails().getTokenUri(),
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(response.getBody());

            String accessToken  = (String) jsonObj.get("access_token");
            String refreshToken = (String) jsonObj.get("refresh_token");
            return new AccessRefreshToken(accessToken,refreshToken);
        } catch (Exception e) {
            e.printStackTrace();
            throw new OAuthException("카카오 인증 오류");
        }
    }

    private MultiValueMap<String, String> tokenRequest(ClientRegistration provider, String code) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("code", code);
        formData.add("grant_type", "authorization_code");
        formData.add("redirect_uri", provider.getRedirectUri());
        formData.add("client_secret", provider.getClientSecret());
        formData.add("client_id",provider.getClientId());
        return formData;
    }


    private String getKakaoEmail(ClientRegistration provider, String accessToken) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + accessToken);
            headers.add("Content-type", "application/x-www-form-urlencoded");


            RestTemplate restTemplate = new RestTemplate();
            HttpEntity httpEntity = new HttpEntity(headers);
            ResponseEntity<String> response = restTemplate.exchange(
                    provider.getProviderDetails().getUserInfoEndpoint().getUri(),
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(response.getBody());

            JSONObject account  = (JSONObject) jsonObj.get("kakao_account");
            String email = (String) account.get("email");

            return email;
        } catch (Exception e) {
            e.printStackTrace();
            // TODO : throw
        }
        return null;
    }
}
