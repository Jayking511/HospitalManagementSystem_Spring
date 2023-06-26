package com.example.demo.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

// Custom deserializer for LocalDate
 public abstract class LocalDateDeserializer extends JsonDeserializer<LocalDate> {
	 
        @Override
        public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            String dateStr = p.getText();
            return LocalDate.parse(dateStr, formatter);
        }
}
