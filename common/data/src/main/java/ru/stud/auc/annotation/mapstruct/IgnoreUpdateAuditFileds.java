package ru.stud.auc.annotation.mapstruct;

import org.mapstruct.Mapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Mapping(target = "insertUserId", ignore = true)
@Mapping(target = "insertDatetime", ignore = true)
@Mapping(target = "updateUserId", ignore = true)
@Mapping(target = "updateDatetime", ignore = true)
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface IgnoreUpdateAuditFileds {}
