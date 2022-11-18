package fcm.practice.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import fcm.practice.dto.MessageDTO;
import fcm.practice.service.FcmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MessageController {


    private final FcmService fcmService;

    String targetToken = "";

    @PutMapping("/message")
    public ResponseEntity<MessageDTO> sendMessage(
        @PathVariable(value = "item-idx") Long itemIdx
    ) throws FirebaseMessagingException {

        fcmService.sendItemActivatedNotice(targetToken,itemIdx);  // 비동기 이므로 바로 message 반환

        return new ResponseEntity<>(new MessageDTO("메세지 전송완료"), HttpStatus.OK);
    }
}
