package moobean.saim.server.global.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import moobean.saim.server.global.annoation.ApiErrorCodes;
import moobean.saim.server.global.exception.code.BaseErrorCode;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;


@Configuration
public class SwaggerConfig {

    private static final String API_NAME = "SAIM API";
    private static final String API_VERSION = "1.0.0";

    @Value("${swagger.server.url}")
    private String serverUrl;

    @Bean
    public OpenAPI getOpenAPI() {
        return new OpenAPI()
                .components(getComponents())
                .servers(List.of(getServer()))
                .security(getSecurity())
                .info(getInfo());
    }

    private Info getInfo() {
        return new Info()
                .title(API_NAME)
                .version(API_VERSION);
    }

    private static List<SecurityRequirement> getSecurity() {
        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("Authorization");

        return Collections.singletonList(securityRequirement);
    }

    private Server getServer() {
        return new Server()
                .url(serverUrl);
    }

    private static Components getComponents() {
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT").name("Authorization")
                .in(SecurityScheme.In.HEADER).name("Authorization");

        return new Components()
                .addSecuritySchemes("Authorization", securityScheme);
    }

    @Bean
    public OperationCustomizer customize() {
        return (Operation operation, HandlerMethod handlerMethod) -> {
            ApiErrorCodes apiErrorCodes =
                    handlerMethod.getMethodAnnotation(ApiErrorCodes.class);

            if (apiErrorCodes != null) {
                generateErrorCodeResponse(operation, apiErrorCodes.value());
            }
            return operation;
        };
    }

    private void generateErrorCodeResponse(Operation operation, Class<? extends BaseErrorCode> type) {
        ApiResponses responses = operation.getResponses();
        BaseErrorCode[] errorCodes = type.getEnumConstants();
        Map<Integer, List<ErrorExampleHolder>> statusWithExampleHolders = Arrays.stream(errorCodes)
                .map(errorCode -> ErrorExampleHolder.builder()
                        .example(getSwaggerExample(errorCode))
                        .customCode(errorCode.getCustomCode())
                        .code(errorCode.getHttpStatus().value())
                        .build())
                .collect(groupingBy(ErrorExampleHolder::getCode));

        addExamplesToResponses(responses, statusWithExampleHolders);
    }


    /**
     * {@code @ApiErrorCodes} 어노테이션이 존재할 경우 {@code ApiResponses}에 {@code Example}를 추가하는 메소드
     *
     * @param responses
     * @param statusWithExampleHolders
     */
    private void addExamplesToResponses(
            ApiResponses responses,
            Map<Integer, List<ErrorExampleHolder>> statusWithExampleHolders
    ) {
        statusWithExampleHolders.forEach(
                (status, v) -> {
                    Content content = new Content();
                    MediaType mediaType = new MediaType();
                    ApiResponse apiResponse = new ApiResponse();

                    v.forEach(
                            exampleHolder -> mediaType.addExamples(
                                    exampleHolder.getCustomCode(),
                                    exampleHolder.getExample()
                            )
                    );

                    content.addMediaType("application/json", mediaType);
                    apiResponse.setContent(content);
                    responses.addApiResponse(String.valueOf(status), apiResponse);
                });
    }

    /**
     * {@code BaseErrorCode}를 통해 {@code Example}를 생성하는 메소드
     *
     * @param errorCode
     * @return
     */
    private Example getSwaggerExample(BaseErrorCode errorCode) {
        ErrorExample errorExample = new ErrorExample(errorCode.getHttpStatus().value(), errorCode.name(), errorCode.getMessage());
        Example example = new Example();
        example.setValue(errorExample);

        return example;
    }
}
