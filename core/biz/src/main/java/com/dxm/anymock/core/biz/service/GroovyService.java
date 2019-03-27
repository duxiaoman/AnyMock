package com.dxm.anymock.core.biz.service;

import groovy.lang.Binding;

public interface GroovyService {
    String exec(Binding binding, String text);
}
