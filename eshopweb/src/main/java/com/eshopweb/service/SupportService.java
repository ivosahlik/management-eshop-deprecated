package com.eshopweb.service;

import com.eshopweb.domain.Support;

import java.util.Optional;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-11-03
 */
public interface SupportService {

    Optional<Support> getOneActiveSupport();

}
