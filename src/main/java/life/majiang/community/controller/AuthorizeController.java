package life.majiang.community.controller;

import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.provioder.GithuProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GithuProvider githuProvider;
    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name="state")String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id("ed937a2e902012511bad");
        accessTokenDTO.setClient_secret("bf9de734d528eec8c9eecbb6b9b3cd9356018b07");
        accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
        githuProvider.getAcessToken(accessTokenDTO);
        return "index";
    }
}
