package com.vassilis.library.repository.converter;

import java.time.LocalTime;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

import com.google.common.base.Strings;

public class LocalTimeConverters {
    @WritingConverter
    public enum LocalTimeToStringConverter implements Converter<LocalTime, String> {
        INSTANCE;

        @Override
        public String convert(LocalTime source) {
            return source == null ? null : source.toString();
        }
    }

    @ReadingConverter
    public enum StringToLocalTimeConverter implements Converter<String, LocalTime> {
        INSTANCE;

        @Override
        public LocalTime convert(String source) {
            return Strings.isNullOrEmpty(source) ? null : LocalTime.parse(source);
        }
    }
}