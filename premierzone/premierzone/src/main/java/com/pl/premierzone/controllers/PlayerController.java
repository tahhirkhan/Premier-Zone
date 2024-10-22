package com.pl.premierzone.controllers;

import com.pl.premierzone.entities.PlayerEntity;
import com.pl.premierzone.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/premier-league")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/health-check")
    public String healthCheck() {
        return "OK";
    }

    @GetMapping("/get-info")
    public List<PlayerEntity> getPlayers(
            @RequestParam(required = false) String team,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String position,
            @RequestParam(required = false) String nation) {

        if (team != null && position != null) {
            return playerService.getPlayersByTeamAndPosition(team, position);
        }
        if (team != null) {
            return playerService.getPlayersByTeam(team);
        }
        if (name != null) {
            return playerService.getPlayersByName(name);
        }
        if (position != null) {
            return playerService.getPlayersByPosition(position);
        }
        if (nation != null) {
            return playerService.getPlayersByNation(nation);
        }
        return playerService.getAllPlayers();
    }

    @PostMapping
    public ResponseEntity<PlayerEntity> addPlayer(@RequestBody PlayerEntity player) {
        PlayerEntity savedPlayer = playerService.savePlayer(player);
        return new ResponseEntity<>(savedPlayer, HttpStatus.CREATED);
    }

    @PutMapping("{playerName}")
    public ResponseEntity<PlayerEntity> updatePlayer(@RequestBody PlayerEntity updatedPlayer, @PathVariable String playerName) {
        PlayerEntity savedPlayer = playerService.updatePlayer(updatedPlayer, playerName);
        return new ResponseEntity<>(savedPlayer, HttpStatus.OK);
    }

    @DeleteMapping("{playerName}")
    public ResponseEntity<String> deletePlayer(@PathVariable String playerName) {
        if (playerService.deletePlayer(playerName)) {
            return new ResponseEntity<>("Player Deleted Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Player Not Found", HttpStatus.NOT_FOUND);
    }
}
