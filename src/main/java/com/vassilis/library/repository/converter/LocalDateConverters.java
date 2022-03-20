package com.vassilis.library.repository.converter;

import java.time.LocalDate;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

import com.google.common.base.Strings;

public class LocalDateConverters {

    @WritingConverter
    public enum LocalDateToStringConverter implements Converter<LocalDate, String> {
        INSTANCE;

        @Override
        public String convert(LocalDate source) {
            return source == null ? null : source.toString();
        }
    }

    @ReadingConverter
    public enum StringToLocalDateConverter implements Converter<String, LocalDate> {
        INSTANCE;

        @Override
        public LocalDate convert(String source) {
            return Strings.isNullOrEmpty(source) ? null : LocalDate.parse(source);
        }
    }
}