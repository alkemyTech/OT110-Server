package com.alkemy.ong.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Objects;
import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
@Getter
public class UpdateFields {
	
	
    public final MessageSource messageSource;
    private boolean hasUpdate = Boolean.FALSE;

    public <T> void updateIfNotBlankAndNotEqual(T source , T destination, Consumer<T> update, String parameterName){
        String notBeBlankMessage = messageSource.getMessage("api.blank", null, Locale.US);
        if (source != null && !source.equals(destination)){
            if (source.getClass().equals(String.class) && ((String) source).isBlank()){
                throw new IllegalArgumentException(String.format("%s %s", parameterName, notBeBlankMessage));
            }
            update.accept(source);
            hasUpdate = Boolean.TRUE;
        }
    }

    public <T> void updateIfNotEmptyAndNotEqual(JsonNullable<T> source , T destination, Consumer<T> update, String parameterName){
        String notBeBlankMessage = messageSource.getMessage("api.blank", null, Locale.US);
        if (source != null) {
            T internalSource = source.orElse(null);
            if (internalSource != null && internalSource.getClass().equals(String.class) && ((String) internalSource).isBlank()) {
                throw new IllegalArgumentException(String.format("%s %s", parameterName, notBeBlankMessage));
            }
            if(!Objects.equals(internalSource, destination)){
                update.accept(internalSource);
                hasUpdate = Boolean.TRUE;
            }
        }
    }
}
