package com.profile.service;


import com.profile.entities.Profile;
import com.profile.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;


    public List<Profile> getProfiles(){
        return profileRepository.findAll();
    }
    public Profile getProfileById(int id) throws Exception{
        return profileRepository.findById(id).orElseThrow(() ->
                new Exception("Invalid Profile ID !")
        );
    }

    public Profile addProfile(Profile profile){

        return profileRepository.save(profile);

    }
    public Profile updateProfile(int id, Profile updatedProfile) {
        Profile existingProfile = profileRepository.findById(id).orElse(null);
        if (existingProfile != null) {
            existingProfile.setName(updatedProfile.getName());
            existingProfile.setEmail(updatedProfile.getEmail());
            existingProfile.setTitle(updatedProfile.getTitle());

            return profileRepository.save(existingProfile);
        } else {

            return null;
        }
    }
    public String deleteProfile(int id){
        profileRepository.deleteById(id);
        return "deleted";
    }
    public List<Profile> searchProfilesByName(String name) {
        return profileRepository.findProfileByName(name);
    }
    public Long getProfileCount() {
        // Implement the logic to get the count of profiles
        return profileRepository.count();
    }
}
