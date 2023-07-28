package com.pragma.powerup.usermicroservice.adapters.driving.http.controller;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.RoleResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IRoleHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleRestController {
    private final IRoleHandler roleHandler;

    @Operation(summary = "get All Roles",
            responses = {
                    @ApiResponse(responseCode = "201", description = "owner created",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "401", description = "You are not authenticated",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @GetMapping("")
    public ResponseEntity<List<RoleResponseDto>> getAllRoles() {
        return ResponseEntity.ok(roleHandler.getAllRoles());
    }
}
