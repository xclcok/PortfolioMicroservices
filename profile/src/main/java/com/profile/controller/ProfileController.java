package com.profile.controller;


import com.profile.entities.Profile;
import com.profile.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200/")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/profiles")
    public List<Profile> getAllProfiles(){
        return profileService.getProfiles();
    }
    @GetMapping("/profiles/{id}")
    public Profile getProfile(@PathVariable("id") int id) throws Exception{
        return profileService.getProfileById(id);
    }

    @PostMapping
    public Profile addProfile(@RequestBody Profile profile){
        return profileService.addProfile(profile);
    }
    @PutMapping("/profiles/{id}")
    public ResponseEntity<Profile> updateProfile(@PathVariable int id, @RequestBody Profile updatedProfile) {
        Profile updated = profileService.updateProfile(id, updatedProfile);

        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/profiles/{id}")
    public String deleteProfileById(@PathVariable("id") int id){
        return profileService.deleteProfile(id);
    }

    @GetMapping("/profiles/search")
    public List<Profile> searchProfilesByName(@RequestParam("name") String name) {
        return profileService.searchProfilesByName(name);
    }

    @GetMapping("/profiles/count")
    public Long getProfileCount() {
        return profileService.getProfileCount();
    }
}
