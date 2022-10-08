package com.svscorp.apiauth.controller;

import com.svscorp.apiauth.service.ApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/case/v1")
@CrossOrigin
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "User", description = "The User API. Contains all the operations that can be performed on a user.")
public class ApiController {

    private final ApiService apiService;

    @GetMapping(value = "/public",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getPublic() {
        return new ResponseEntity<>(apiService.getPublicString(), HttpStatus.OK);
    }
    @GetMapping(value = "/protected",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getProtected() {
        return new ResponseEntity<>(apiService.getProtectedString(), HttpStatus.OK);
    }
    @Operation(summary = "Get a book by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content) })
    @PreAuthorize("hasAuthority('create:items')")
    @GetMapping(value = "/admin",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAdmin() {
        return new ResponseEntity<>(apiService.getAdminString(), HttpStatus.OK);
    }

}
