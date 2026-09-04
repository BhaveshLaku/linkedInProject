package com.bhavesh.linkedInProject.connectionService.controller;

import com.bhavesh.linkedInProject.connectionService.entity.Person;
import com.bhavesh.linkedInProject.connectionService.service.ConnectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
public class ConnectionController {

    private final ConnectionService connectionService;

    @GetMapping("/{userId}/first-degree")
    public ResponseEntity<List<Person>> getFirstDegreeConnections(@PathVariable Long userId) {
        return ResponseEntity.ok(connectionService.getFirstDegreeConnectionsOfUser(userId));
    }

}
