package com.buildrun.gameoflife.controller;

import com.buildrun.gameoflife.GameOfLife;
import com.buildrun.gameoflife.dto.ExecSimulacaoRequest;
import com.buildrun.gameoflife.dto.SimulacaoRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/gol")
public class GolController {

    private GameOfLife gol;
    private int dir = 1;

    @PostMapping("/start")
    public int[][] start(@RequestBody SimulacaoRequest req) {
        gol = new GameOfLife(req.width(), req.height());
        dir = (req.direction() == null) ? 1 : req.direction();

        boolean ok = gol.naColuna(req.map());
        if (!ok) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mapa inválido.");

        return gol.getGrid();
    }

    @GetMapping("/state")
    public int[][] state() {
        if (gol == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Roda /start primeiro.");
        return gol.getGrid();
    }

    @PostMapping("/run")
    public int[][] run(@RequestBody(required = false) ExecSimulacaoRequest req) {
        if (gol == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Roda /start primeiro.");

        int passos = (req == null || req.steps() == null) ? 1 : req.steps();
        int direcao = (req == null || req.direction() == null) ? dir : req.direction();

        if (passos <= 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "steps tem que ser > 0.");
        if (direcao < 1 || direcao > 4) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "direction tem que ser 1..4.");

        for (int i = 0; i < passos; i++) gol.step(direcao);

        return gol.getGrid();
    }
}