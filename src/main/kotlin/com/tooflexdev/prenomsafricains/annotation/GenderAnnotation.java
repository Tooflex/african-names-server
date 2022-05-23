/*
 * Copyright (c) 2021.
 * @Author Otourou Da Costa
 */

package com.tooflexdev.prenomsafricains.annotation;

import com.tooflexdev.prenomsafricains.domain.Gender;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@MyAnnotation()
public @interface GenderAnnotation {

    Gender value(); // no default has user define default value

}
