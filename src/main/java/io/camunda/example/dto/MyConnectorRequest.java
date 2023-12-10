package io.camunda.example.dto;

import io.camunda.connector.generator.annotation.TemplateProperty;
import io.camunda.connector.generator.annotation.TemplateProperty.PropertyType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record MyConnectorRequest(
     @TemplateProperty(group = "compose", type = PropertyType.Text , label = "Flight_id") String Flight_id ,
     @TemplateProperty(group = "compose", type = PropertyType.Text , label = "recipientEmail") String recipientEmail
) {}
