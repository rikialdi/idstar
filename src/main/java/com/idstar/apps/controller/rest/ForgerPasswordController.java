package com.idstar.apps.controller.rest;

import com.idstar.apps.dto.ResetPasswordModel;
import com.idstar.apps.entity.oauth.User;
import com.idstar.apps.repository.oauth.UserRepository;
import com.idstar.apps.service.UserService;
import com.idstar.apps.service.email.EmailSender;
import com.idstar.apps.utils.Config;
import com.idstar.apps.utils.EmailTemplate;
import com.idstar.apps.utils.Response;
import com.idstar.apps.utils.SimpleStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/forget-password/")
public class ForgerPasswordController {

    @Autowired
    private UserRepository userRepository;

    Config config = new Config();

    @Autowired
    public UserService serviceReq;

    @Value("${expired.token.password.minute:}")//FILE_SHOW_RUL
    private int expiredToken;

    @Autowired
    public Response templateCRUD;

    @Autowired
    public EmailTemplate emailTemplate;

    @Autowired
    public EmailSender emailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Step 1 : Send OTP
    @PostMapping("/send")//send OTP
    public Map sendEmailPassword(@Valid @RequestBody ResetPasswordModel user) {
        String message = "Thanks, please check your email";

        if (StringUtils.isEmpty(user.getUsername())) return templateCRUD.templateEror("No email provided");
        User found = userRepository.findOneByUsername(user.getUsername());
        if (found == null) return templateCRUD.notFound("Email not found"); //throw new BadRequest("Email not found");

        String template = emailTemplate.getResetPassword();
        if (StringUtils.isEmpty(found.getOtp())) {
            User search;
            String otp;
            do {
                otp = SimpleStringUtils.randomString(6, true);
                search = userRepository.findOneByOTP(otp);
            } while (search != null);
            Date dateNow = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateNow);
            calendar.add(Calendar.MINUTE, expiredToken);
            Date expirationDate = calendar.getTime();

            found.setOtp(otp);
            found.setOtpExpiredDate(expirationDate);
            template = template.replaceAll("\\{\\{PASS_TOKEN}}", otp);
            template = template.replaceAll("\\{\\{USERNAME}}", (found.getUsername() == null ? "" +
                    "@UserName"
                    :
                     found.getUsername()));

            userRepository.save(found);
        } else {
            template = template.replaceAll("\\{\\{USERNAME}}", (found.getUsername() == null ? "" +
                    "@UserName"
                    :
                    found.getUsername()));
            template = template.replaceAll("\\{\\{PASS_TOKEN}}", found.getOtp());
        }
        emailSender.sendAsync(found.getUsername(), "idstar - Forget Password", template);


        return templateCRUD.templateSukses("success");

    }

    //Step 2 : CHek TOKEN OTP EMAIL
    @PostMapping("/validate")
    public Map cheKTOkenValid(@RequestBody ResetPasswordModel model) {
        if (model.getOtp() == null) return templateCRUD.notFound("Token is required");

        User user = userRepository.findOneByOTP(model.getOtp());
        if (user == null) {
            return templateCRUD.templateEror("Token not valid");
        }

        return templateCRUD.templateSukses("Success");
    }

    // Step 3 : lakukan reset password baru
    @PostMapping("/change-password")
    public Map<String, String> resetPassword(@RequestBody ResetPasswordModel model) {
        if (model.getOtp() == null) return templateCRUD.notFound("Token is required");
        if (model.getNewPassword() == null) return templateCRUD.notFound("New Password  is required");
        User user = userRepository.findOneByOTP(model.getOtp());
        String success;
        if (user == null) return templateCRUD.notFound("Token not valid");

        user.setPassword(passwordEncoder.encode(model.getNewPassword().replaceAll("\\s+", "")));
        user.setOtpExpiredDate(null);
        user.setOtp(null);

        try {
            userRepository.save(user);
            success = "success";
        } catch (Exception e) {
            return templateCRUD.templateEror("Gagal simpan user");
        }
        return templateCRUD.templateSukses(success);
    }


}
