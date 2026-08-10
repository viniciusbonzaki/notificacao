package com.javanauta.notificacao.controller;

import com.javanauta.notificacao.business.EmailService;
import com.javanauta.notificacao.business.dtos.TarefaDTO;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<Void> enviarEmail(@RequestBody TarefaDTO tarefaDTO) {
        emailService.enviarEmail(tarefaDTO);
        return ResponseEntity.ok().build();
    }
}
