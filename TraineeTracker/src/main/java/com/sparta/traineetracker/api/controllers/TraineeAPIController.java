package com.sparta.traineetracker.api.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sparta.traineetracker.api.dtos.NewBatchDTO;
import com.sparta.traineetracker.api.dtos.NewTraineeDTO;
import com.sparta.traineetracker.api.dtos.TrackerDTO;
import com.sparta.traineetracker.api.dtos.TraineeDTO;
import com.sparta.traineetracker.api.utils.DTOConverter;
import com.sparta.traineetracker.api.utils.JSONSerializer;
import com.sparta.traineetracker.entities.*;
import com.sparta.traineetracker.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class TraineeAPIController {

    @Autowired
    private BatchRepository batchRepo;

    @Autowired
    private UserBatchRepository userBatchRepo;

    @Autowired
    private AccountRepository accountRepo;


    @Autowired
    private ApikeyRepository apikeyRepo;

    @Autowired
    private TrackerRepository trackerRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CourseRepository courseRepo;

   @GetMapping("api/trainees/{batchName}")
    public ResponseEntity<String> getAllTrainees(@PathVariable String batchName, @RequestHeader(required = false) String key){

       HttpHeaders headers=new HttpHeaders();
       headers.add("Content-Type","application/json");

       if(key==null || apikeyRepo.findByApikey(key)==null ){
           return new ResponseEntity<String>("{\"message\":\"Unauthorised access \"}",headers, HttpStatus.UNAUTHORIZED);
       }


       ObjectMapper mapper=new ObjectMapper();

       Batch batch=batchRepo.findByBatchName(batchName);
       List<UserBatch> myList=userBatchRepo.findByBatch(batch);

       List<TraineeDTO> trainees=new ArrayList<>();

       List<EntityModel> myentityList=new ArrayList<>();

       for(UserBatch ub:myList){
           User user=ub.getUser();
           Account account=accountRepo.findByUser(user);
           if(account.getRole().equals("trainee")){


               EntityModel<TraineeDTO> entityModel=EntityModel.of(DTOConverter.toTraineeDTO(user));
               WebMvcLinkBuilder trackerLink=WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).getTracker(user.getId(),"abc"));
               entityModel.add(trackerLink.withRel("Tracker"));
               myentityList.add(entityModel);

               trainees.add(DTOConverter.toTraineeDTO(user));
           }
       }

       String traineesString= JSONSerializer.serialiseEntityList(myentityList);

       return new ResponseEntity<>(traineesString,headers, HttpStatus.OK);

   }

   @GetMapping("api/trainee/{id}")
   public ResponseEntity<String> getTraineeById(@PathVariable int id){

       HttpHeaders headers=new HttpHeaders();
       headers.add("Content-Type","application/json");
       ObjectMapper mapper=new ObjectMapper();

        User trainee=userRepo.findById(id).get();

        String traineeString=JSONSerializer.serialiseUser(trainee);

       return new ResponseEntity<>(traineeString,headers,HttpStatus.OK);


   }


   @GetMapping("api/tracker/{id}")
    public ResponseEntity<String> getTracker(@PathVariable int id,@RequestHeader(required = false) String key){

       HttpHeaders headers=new HttpHeaders();
       headers.add("Content-Type","application/json");

       if(key==null || apikeyRepo.findByApikey(key)==null ){
           return new ResponseEntity<String>("{\"message\":\"Unauthorised access \"}",headers, HttpStatus.UNAUTHORIZED);
       }

       User user=userRepo.findById(id).get();
       List<Tracker> trackerList=trackerRepo.findByTrainee(user);
       List<TrackerDTO> trackerDTOList=new ArrayList<>();

       for(Tracker t:trackerList){
           trackerDTOList.add(DTOConverter.toTrackerDTO(t));
       }

       WebMvcLinkBuilder traineeLink=WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).getTraineeById(id));
       CollectionModel<TrackerDTO> collectionModel=CollectionModel.of(trackerDTOList);
       collectionModel.add(traineeLink.withRel("Trainee"));


       ObjectMapper mapper=new ObjectMapper();

       String trackerString=JSONSerializer.serialiseCollection(collectionModel);

       return new ResponseEntity<>(trackerString,headers, HttpStatus.OK);

   }


   @PostMapping("api/addbatch")
    public ResponseEntity<String> addNewbatch(@RequestBody NewBatchDTO newBatchDTO,@RequestHeader(required = false) String key){


       HttpHeaders headers=new HttpHeaders();
       headers.add("Content-Type","application/json");

       if(key==null || apikeyRepo.findByApikey(key)==null ){
           return new ResponseEntity<String>("{\"message\":\"Unauthorised access \"}",headers, HttpStatus.UNAUTHORIZED);
       }

       Course course=courseRepo.findByCourseName(newBatchDTO.getCourseName());

       if(batchRepo.findByBatchName(newBatchDTO.getBatchName())==null){

           Batch newbatch=new Batch();
           newbatch.setCourse(course);
           newbatch.setBatchName(newBatchDTO.getBatchName());
           newbatch.setWeeks(newBatchDTO.getNumOfWeeks());

           //save the batch to DB
           batchRepo.save(newbatch);
       }

       Batch newlyAddedBatch=batchRepo.findByBatchName(newBatchDTO.getBatchName());

        for(NewTraineeDTO bDTO:newBatchDTO.getNewTrainees()){

            User trainee=new User();
            trainee.setFirstName(bDTO.getFirstName());
            trainee.setLastName(bDTO.getLastName());

            //save trainee to DB
            userRepo.save(trainee);
            User newlyAddedTrainee=userRepo.findByFirstNameAndLastName(bDTO.getFirstName(), bDTO.getLastName());

            //updating user_batch table
            UserBatch newUserBatch=new UserBatch();
            newUserBatch.setUser(newlyAddedTrainee);
            newUserBatch.setBatch(newlyAddedBatch);

            userBatchRepo.save(newUserBatch);

            //upadting Account table

            Account newAccount=new Account();
            newAccount.setUser(newlyAddedTrainee);
            newAccount.setUsername(bDTO.getUserName());
            newAccount.setPassword(bDTO.getPassword());
            newAccount.setRole(bDTO.getUserRole());

            accountRepo.save(newAccount);

            for(int i=1;i<=newBatchDTO.getNumOfWeeks();i++){
                Tracker newTracker=new Tracker();
                newTracker.setWeek(i);
                newTracker.setTrainee(newlyAddedTrainee);

                trackerRepo.save(newTracker);
            }


        }

        return new ResponseEntity<>("{\"message\":\" Trainees successfully added to the database\"}",headers,HttpStatus.OK);

   }


}
