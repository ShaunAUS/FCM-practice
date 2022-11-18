package fcm.practice.commons;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.InputStream;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FcmInitializer {

    //firebase에서 받은 인증키
    @Value("${fcm.certification}")
    private String credential;

    @PostConstruct // 스프링 실행단계에 실행
    public void initialize(){


        ClassPathResource resource = new ClassPathResource(credential);

        //인증키로 인증 -> 인증한 객체
        try (InputStream stream = resource.getInputStream()) {
            FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(stream))
                .build();

            //새로운 객체? 로 initialize
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                log.info("FirebaseApp initialization complete");
            }
        }catch (Exception e){
            e.printStackTrace();
            //throw new ApiException();
        }

    }
}
