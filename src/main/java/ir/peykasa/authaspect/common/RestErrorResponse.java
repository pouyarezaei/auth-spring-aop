package ir.peykasa.authaspect.common;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yaser(amin) Sadeghi
 */
@NoArgsConstructor
public class RestErrorResponse {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestErrorResponse.class);

    private String key;
    private List<Object> messages = new ArrayList<>();

    public RestErrorResponse(String key, List<?> messages) {
        this.key = key;
        this.messages.addAll(messages);
    }

    public RestErrorResponse(String key, String message) {
        this.key = key;
        this.messages.add(message);
    }

    public String getKey() {
        return key;
    }

    public List<Object> getMessages() {
        return messages;
    }

    @Override
    public String toString() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.setVisibility(mapper.getSerializationConfig().getDefaultVisibilityChecker().withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                    .withGetterVisibility(JsonAutoDetect.Visibility.NONE));
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException ignored) {
            LOGGER.error("",ignored);
            return "RestErrorResponse{" +
                    "key='" + key + '\'' +
                    ", messages=" + messages +
                    '}';
        }
    }
}
