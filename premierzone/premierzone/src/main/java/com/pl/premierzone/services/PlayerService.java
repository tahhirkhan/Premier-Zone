package com.pl.premierzone.services;

import com.pl.premierzone.entities.PlayerEntity;
import com.pl.premierzone.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<PlayerEntity> getAllPlayers() {
        return playerRepository.findAll();
    }

    public List<PlayerEntity> getPlayersByTeam(String team) {
/*
        return playerRepository.findAll().stream()
                .filter(playerEntity -> team.equals(playerEntity.getTeam()))
                .collect(Collectors.toList());
*/
        List<PlayerEntity> list = new ArrayList<>();
        for (PlayerEntity playerEntity : playerRepository.findAll()) {
            if (team.equals(playerEntity.getTeam_name())) {
                list.add(playerEntity);
            }
        }
        return list;
    }

    public List<PlayerEntity> getPlayersByName(String name) {
        List<PlayerEntity> list = new ArrayList<>();
        for (PlayerEntity playerEntity : playerRepository.findAll()) {
            if (playerEntity.getName().toLowerCase().contains(name)) {
                list.add(playerEntity);
            }
        }
        return list;
    }

    public List<PlayerEntity> getPlayersByPosition(String position) {
        List<PlayerEntity> list = new ArrayList<>();
        for (PlayerEntity playerEntity : playerRepository.findAll()) {
            if (playerEntity.getPosition().toLowerCase().contains(position)) {
                list.add(playerEntity);
            }
        }
        return list;
    }

    public List<PlayerEntity> getPlayersByNation(String nation) {
        List<PlayerEntity> list = new ArrayList<>();
        for (PlayerEntity playerEntity : playerRepository.findAll()) {
            if (playerEntity.getNation().toLowerCase().contains(nation)) {
                list.add(playerEntity);
            }
        }
        return list;
    }

    public List<PlayerEntity> getPlayersByTeamAndPosition(String team, String position) {
        List<PlayerEntity> list = new ArrayList<>();
        for (PlayerEntity playerEntity : playerRepository.findAll()) {
            if (playerEntity.getTeam_name().equals(team) && playerEntity.getPosition().equals(position)) {
                list.add(playerEntity);
            }
        }
        return list;
    }

    public PlayerEntity savePlayer(PlayerEntity player) {
        playerRepository.save(player);
        return player;
    }

    public PlayerEntity updatePlayer(PlayerEntity updatedPlayer, String existingPlayerName) {

        Optional<PlayerEntity> existingPlayerContainer = playerRepository.findByName(existingPlayerName);

        if (existingPlayerContainer != null) {

            PlayerEntity playerToUpdate = existingPlayerContainer.get();

//            playerToUpdate.setName(updatedPlayer.getName()); // don't update the primary key, otherwise PUT request will not work

            playerToUpdate.setNation(updatedPlayer.getNation());
            playerToUpdate.setPosition(updatedPlayer.getPosition());
            playerToUpdate.setAge(updatedPlayer.getAge());
            playerToUpdate.setMatches_played(updatedPlayer.getMatches_played());
            playerToUpdate.setStarts(updatedPlayer.getStarts());
            playerToUpdate.setMinutes_played(updatedPlayer.getMinutes_played());
            playerToUpdate.setGoals(updatedPlayer.getGoals());
            playerToUpdate.setAssists(updatedPlayer.getAssists());
            playerToUpdate.setPenalties_scored(updatedPlayer.getPenalties_scored());
            playerToUpdate.setYellow_cards(updatedPlayer.getYellow_cards());
            playerToUpdate.setRed_cards(updatedPlayer.getRed_cards());
            playerToUpdate.setExpected_goals(updatedPlayer.getExpected_goals());
            playerToUpdate.setExpected_assists(updatedPlayer.getExpected_assists());
            playerToUpdate.setTeam_name(updatedPlayer.getTeam_name());

            playerRepository.save(playerToUpdate);
            return playerToUpdate;
        }
        return null;
    }

    public boolean deletePlayer(String playerName) {
        Optional<PlayerEntity> playerContainer = playerRepository.findByName(playerName);
        if (playerContainer.isPresent()) {
            PlayerEntity playerToDelete = playerContainer.get();
            playerRepository.delete(playerToDelete);
            return true;
        }
        return false;
    }
}
