package com.example.spring_security_cognito_240424.controllers;

import com.example.spring_security_cognito_240424.models.Task;
import com.example.spring_security_cognito_240424.repositories.TaskRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminDeleteUserRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@Controller
public class AccountController {

    private final TaskRepository taskRepository;

    public AccountController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/delete-account")
    public String getDeleteAccountPage(Model model, HttpServletResponse response) throws IOException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated() && authentication.getPrincipal() instanceof OidcUser) {
            OidcUser user = (OidcUser) authentication.getPrincipal();
            model.addAttribute("oidcUser", user);
            return "delete-account";
        } else {
            response.sendRedirect("/oauth2/authorization/cognito");
            return null;
        }
    }

    @PostMapping("/delete-account")
    public String postDeleteAccount(OAuth2AuthenticationToken token, HttpServletRequest request, HttpServletResponse response){
        var userPoolId = System.getProperty("aws.cognito.poolId"); // get the value of the aws.cognito.poolId property
        String username = token.getName();
        taskRepository.deleteByCognitoUsername(username);

        try {
            // Delete user from AWS Cognito
            CognitoIdentityProviderClient cognitoClient = CognitoIdentityProviderClient.builder()
                    .region(Region.EU_NORTH_1) // replace with your Cognito region
                    .build();

            AdminDeleteUserRequest deleteUserRequest = AdminDeleteUserRequest.builder()
                    .userPoolId(userPoolId) // replace with your user pool ID
                    .username(username)
                    .build();

            cognitoClient.adminDeleteUser(deleteUserRequest);

            // Invalidate session
            new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
        }

        return "redirect:/";
    }
}