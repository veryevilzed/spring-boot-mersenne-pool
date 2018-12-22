package ru.veryevilzed.tools.rng;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({MersennePoolConfiguration.class})
@Documented
public @interface EnableMersennePool {
}

