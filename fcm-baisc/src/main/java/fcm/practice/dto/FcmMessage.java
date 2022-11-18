package fcm.practice.dto;

import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
public class FcmMessage {

    private boolean validate_only;
    private Message message;

    @Builder
    @AllArgsConstructor
    @Getter
    public static class Message{

        // 모든 모바일 os 가능
        private Notification notification;

        //특정 디바이스
        private String token;

        private Map<String,String> data;
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class Notification   {
        private String title;
        private String body;
        private String image;
    }


}
