package br.com.kimae;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class MyOwnService implements Serializable {

    public void teste(){
        log.info("injetado ;D");
    }
}
