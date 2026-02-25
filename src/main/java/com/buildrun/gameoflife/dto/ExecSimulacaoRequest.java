package com.buildrun.gameoflife.dto;

public record ExecSimulacaoRequest(
        Integer steps,
        Integer direction
) {}