package auth.service.utilities;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class EmailUtil {
    @Autowired
    private JavaMailSender mailSender;

    /// Gui email bang string thuan
    public void sendEmail_I(String to, String subject, String content) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(content);

            mailSender.send(message);
        } catch (Exception e) {
            log.error("Email send error {}", e.getMessage());
        }
    }

    /// Gui HTML qua email
    public void sendEmail_II(String to, String subject, String token) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        String link = "http://localhost:8080/api/v1/auth/verify-account?token=" + token;
        String html = """
                    <div style="padding: 10px">
                        <h1>Verify Account</h1>
                        <p>
                            Click here to activate your account:
                            <a href="%s" style="font-weight: 800">Verify Now</a>
                        </p>
                    </div>
                """.formatted(link);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(html, true);

        mailSender.send(message);
    }
}
