package com.appleshopingmall.controller.web.adminForm;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DirectType {
    MacBook("MacBook"), iPad("iPad"), iPhone("iPhone"), watch("watch");

    private final String name;
}
