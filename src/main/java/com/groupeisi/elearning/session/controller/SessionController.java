package com.groupeisi.elearning.session.controller;


import com.groupeisi.elearning.session.dtos.SessionRequestDto;
import com.groupeisi.elearning.session.dtos.SessionResponseDTO;
import com.groupeisi.elearning.session.service.SessionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@RestController
@RequestMapping("/sessions")
public class SessionController {

    private SessionService sessionsService;



    @PostMapping
    public ResponseEntity<SessionResponseDTO> createSession(@Valid @RequestBody SessionRequestDto requestDTO) {
        SessionResponseDTO createdSession = sessionsService.createSession(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSession);
    }


    @GetMapping("/{id}")
    public ResponseEntity<SessionResponseDTO> getSession(@PathVariable Long id) {
        SessionResponseDTO session = sessionsService.getSession(id);
        return ResponseEntity.ok(session);
    }


    @GetMapping
    public ResponseEntity<List<SessionResponseDTO>> getAllSessions() {
        List<SessionResponseDTO> sessions = sessionsService.getAllSessions();
        return ResponseEntity.ok(sessions);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SessionResponseDTO> updateSession(@PathVariable Long id,
                                                            @Valid @RequestBody SessionRequestDto requestDTO) {
        SessionResponseDTO updatedSession = sessionsService.updateSession(id, requestDTO);
        return ResponseEntity.ok(updatedSession);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Long id) {
        sessionsService.deleteSession(id);
        return ResponseEntity.noContent().build();
    }
}