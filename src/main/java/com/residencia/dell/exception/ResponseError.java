package com.residencia.dell.exception;

import java.time.LocalDateTime;

public class ResponseError {

    private Integer status;
    private String title;
    private LocalDateTime timeStamp;

    //Constructor
    public ResponseError(Integer status, String titulo, LocalDateTime dataHora) {
        super();
        this.status = status;
        this.title = titulo;
        this.timeStamp = dataHora;
    }

    //Getters & Setters
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitulo() {
        return title;
    }

    public void setTitulo(String titulo) {
        this.title = titulo;
    }

    public LocalDateTime getDataHora() {
        return timeStamp;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.timeStamp = dataHora;
    }
}
