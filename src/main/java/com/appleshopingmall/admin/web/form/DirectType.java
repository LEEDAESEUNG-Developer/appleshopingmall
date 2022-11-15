package com.appleshopingmall.admin.web.form;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DirectType {
    MacBook("MacBook"), iPad("iPad"), iPhone("iPhone"), watch("watch");

    private final String name;
}
