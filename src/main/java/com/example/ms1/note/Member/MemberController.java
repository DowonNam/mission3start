package com.example.ms1.note.Member;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Getter
    @Setter
    public class MemberCreateForm {

        @NotEmpty(message = "사용자ID는 필수항목입니다.")
        private String userId;

        @NotEmpty(message = "비밀번호는 필수항목입니다.")
        private String password;

        @NotEmpty(message = "닉네임은 필수항목입니다.")
        private String nickname;

        @NotEmpty(message = "이메일은 필수항목입니다.")
        @Email(message = "이메일 형식이 아닙니다.")
        private String email;
    }
    @GetMapping("/signup")
    public String signup(MemberCreateForm memberCreateForm) {
        return "signup_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid MemberCreateForm memberCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup_form";
        }

        memberService.create(memberCreateForm.getUserId(),
               memberCreateForm.getPassword(),memberCreateForm.getNickname(), memberCreateForm.getEmail());

        return "redirect:/";
    }
    @GetMapping("/login")
    public String login() {
        return "login_form";
    }
}
