package io.camunda.example;

import io.camunda.connector.api.annotation.OutboundConnector;
import io.camunda.connector.api.error.ConnectorException;
import io.camunda.connector.api.outbound.OutboundConnectorContext;
import io.camunda.connector.api.outbound.OutboundConnectorFunction;
import io.camunda.connector.generator.annotation.ElementTemplate;
import io.camunda.example.dto.MailService;
import io.camunda.example.dto.MyConnectorRequest;
import io.camunda.example.dto.MyConnectorResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


@OutboundConnector(
    name = "MYCONNECTOR",
    inputVariables = {"recipientEmail"},
    type = "inform_staff")

@ElementTemplate(
    id = "io.camunda.connector.Template.v1",
    name = "Template connector",
    version = 1,
    description = "Describe this connector",
    icon = "icon.svg",
    documentationRef = "https://docs.camunda.io/docs/components/connectors/out-of-the-box-connectors/available-connectors-overview/",
    propertyGroups = {
      @ElementTemplate.PropertyGroup(id = "authentication", label = "Authentication"),
      @ElementTemplate.PropertyGroup(id = "compose", label = "Compose")
    },
    inputDataClass = MyConnectorRequest.class)
public class MyConnectorFunction implements OutboundConnectorFunction {
  private final MailService mailService;

  @Autowired
  public MyConnectorFunction(MailService mailService) {
    this.mailService = mailService;
  }

  private static final Logger LOGGER = LoggerFactory.getLogger(MyConnectorFunction.class);

  @Override
  public Object execute(OutboundConnectorContext context) {
    final var connectorRequest = context.bindVariables(MyConnectorRequest.class);
    return executeConnector(connectorRequest);
  }
  
  private MyConnectorResult executeConnector(final MyConnectorRequest connectorRequest) {
    // TODO: implement connector logic
    LOGGER.info("Executing my connector with request {}", connectorRequest);

    String recipientEmail = connectorRequest.recipientEmail();
    System.out.println("this is the email : " +recipientEmail);
   /* if (message != null && message.toLowerCase().startsWith("fail")) {
      throw new ConnectorException("FAIL", "My property started with 'fail', was: " + message);
    }*/

    mailService.sendEmail(recipientEmail, "Test Subject", "Boarding Allowed");
    return new MyConnectorResult(recipientEmail);
  }
}
