package fcm.practice.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FcmService {

    //알림 보내기
    @Async
    @Transactional
    public void sendNotification(String targetToken, String title, String body, String route)
        throws FirebaseMessagingException {

        //토큰이 없으면
        if (targetToken == null) {
            return;
        }

        Notification notification = Notification.builder()
            .setTitle(title)
            .setBody(body)
            .build();


        Message message = Message.builder()
            .setNotification(notification) // 알림이 아닌 데이터만 전송할꺼면 여기 생략
            .setToken(targetToken)
            .putData("route", route)
            .build();

        String response = FirebaseMessaging.getInstance().send(message);


        //DB 저장시 사용
/*        try {
            String response = FirebaseMessaging.getInstance().send(message);

            NoticeVO notice = NoticeVO.builder()
                .user(user)
                .content(body)
                .route(route)
                .state(1)
                .title(title)
                .build();

            noticeUpdateService.save(notice);
        } catch (Exception e) {
            log.warn(user.getEmail() + ": 알림 전송에 실패하였습니다.");
        }*/
    }

    @Async
    @Transactional
    public void sendItemActivatedNotice(String targetToken, Long itemIdx) throws FirebaseMessagingException {

        // 해당 유저 fcm 토큰으로 메세지 보내기
        sendNotification(targetToken,
            "푸시 메세지 테스트 제목",
            "푸시 메세지 테스트 바디",
            "/");
    }
}
