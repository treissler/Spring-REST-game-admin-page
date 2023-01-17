package com.game.controller;

import com.game.entity.PlayerForm;
import com.game.entity.PlayerEntity;
import com.game.entity.PlayerFilter;
import com.game.entity.PlayerPageable;
import com.game.exception.NotValidIdException;
import com.game.exception.PlayerNotFoundException;
import com.game.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;

@RestController
@RequestMapping("/rest")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @GetMapping("/players/{id}")
    public ResponseEntity getPlayersById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(playerService.getPlayerById(id));
        } catch (PlayerNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (NotValidIdException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }

    @DeleteMapping("/players/{id}")
    public ResponseEntity deletePlayerById(@PathVariable Long id){
        try{
            playerService.deletePlayerById(id);
            return ResponseEntity.ok("Deleted successful, player id " + id);
        } catch (PlayerNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (NotValidIdException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }

    @PostMapping("/players")
    @ResponseBody
    public ResponseEntity createPlayer(@RequestBody PlayerForm playerForm)
    {
        try{
            PlayerEntity playerEntity = playerService.createPlayer(playerForm);
            return ResponseEntity.ok(playerEntity);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }

    @PostMapping("/players/{id}")
    @ResponseBody
    public ResponseEntity updatePlayer(@PathVariable Long id, @RequestBody PlayerForm playerForm){
        try{
            PlayerEntity playerEntity = playerService.updatePlayer(id, playerForm);
            return ResponseEntity.ok(playerEntity);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (NotValidIdException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (PlayerNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }

    @GetMapping("/players/count")
    @Transactional
    public ResponseEntity getPlayersById(PlayerFilter playerFilter){
        try {
            Integer count = playerService.getCount(playerFilter);
            return ResponseEntity.ok(count);
        } catch (Exception e){
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }

    @GetMapping("/players")
    @Transactional
    public ResponseEntity getPlayersFiltered(PlayerFilter filter, PlayerPageable pagination){
        try {
            Page<PlayerEntity> paginated = playerService.getPlayersFiltered(filter, pagination);
            return ResponseEntity.ok(paginated.getContent());
        } catch (Exception e){
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }

}