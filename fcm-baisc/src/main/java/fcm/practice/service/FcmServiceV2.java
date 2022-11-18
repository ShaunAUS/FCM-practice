/*
package fcm.practice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import java.io.IOException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;

public class FcmServiceV2 {

    private final String API_URL = "https://fcm.googleapis.com/v1/projects/ID/messages:send";
    private final ObjectMapper obj;




    //targetToken (디바이스)에 메세지 보냄
    public void sendMessageTo(String targetToken, String title, String body) throws IOException {

        String message = makeMessage(targetToken, title, body);

        OkHttpClient client = new OkHttpClient();

        //요청바디
        RequestBody requestBody = RequestBody
            .create(message, MediaType.get("application/json; charset=utf-8"));

        //요청 to API
        Request request = new Request.Builder()
            .url(API_URL)
            .post(requestBody)
            .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
            .addHeader(HttpHeaders.CONTENT_TYPE, "application/json; UTF-8")
            .build();

        //응답
        Response response = client.newCall(request)
            .execute();

        System.out.println(response.body().string());
    }

    private String getAccessToken() throws IOException {

        //firebase에서 받은 인증키
        String fireBaseConfigPath = "firebase/firebase-service-key.json";

        //인증키로 인증
        //GoogleAPI 를 사용하기 위한 oauth2를 이용해 인증한 대상 나타내는 객체
        GoogleCredentials googleCredentials = GoogleCredentials
            .fromStream(new ClassPathResource(fireBaseConfigPath).getInputStream())
            .createDelegated("https://www.googleapis.com/auth/cloud-platform");

        //인증서버에서 필요로 하는 권한 (재)설정시
        //googleCredentials.createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));

        //인증 객체로 토큰 가져오기
        googleCredentials.refreshIfExpired();

        //인증 토큰 가져오기
        //인증 토큰은 컨트룰러에서 FCM 사용시 Header에 설정 예정
        return googleCredentials.getAccessToken().getTokenValue();

    }

    //메세지 구성
    private String makeMessage(String targetToken, String title, String body) throws JsonProcessingException {

        FcmMessage fcmMessage = FcmMessage.builder()
            .message(FcmMessage.Message.builder()
                .token(targetToken)
                .notification(FcmMessage.Notification.builder()
                    .title(title)
                    .body(body)
                    .image(null)
                    .build()
                )
                .build()
            )
            .validate_only(false)
            .build();

        return obj.writeValueAsString(fcmMessage);
    }
}
*/
