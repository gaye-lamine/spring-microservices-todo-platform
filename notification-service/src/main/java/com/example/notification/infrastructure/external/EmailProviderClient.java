package com.example.notification.infrastructure.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Interacts with an external email service for sending notifications.
 * This client handles the communication with the email provider's API.
 */
@Component
public class EmailProviderClient {

    private final RestTemplate restTemplate;
    private final String emailServiceUrl;

    public EmailProviderClient(RestTemplate restTemplate,
                                @Value("${email.service.url}") String emailServiceUrl) {
        this.restTemplate = restTemplate;
        this.emailServiceUrl = emailServiceUrl;
    }

    /**
     * Sends an email notification to the specified recipient.
     *
     * @param recipient the email address of the recipient
     * @param subject   the subject of the email
     * @param body      the body content of the email
     * @return response from the email service
     */
    public String sendEmail(String recipient, String subject, String body) {
        // Implementation for sending email using the external service
        // This would typically involve creating a request object and sending it via restTemplate
        // For example:
        // EmailRequest emailRequest = new EmailRequest(recipient, subject, body);
        // return restTemplate.postForObject(emailServiceUrl, emailRequest, String.class);
        return "Email sent";
    }
}