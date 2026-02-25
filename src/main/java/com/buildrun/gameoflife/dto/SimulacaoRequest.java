package com.buildrun.gameoflife.dto;

public record SimulacaoRequest(
        Integer width,
        Integer height,
        String map,
        Integer direction
) {}