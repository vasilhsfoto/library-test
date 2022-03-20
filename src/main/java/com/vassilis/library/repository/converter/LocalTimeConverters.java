package com.vassilis.library.repository.converter;

public class LocalTimeConverters {
}/*{

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
}*/