package com.example.cats.Controller;

import com.example.cats.Model.Cat;
import com.example.cats.Repository.CatRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/cats")
public class CatController {

    private final CatRepository repository;

    public CatController(CatRepository repository) {
        this.repository = repository;
    }

    @PostMapping()
    public Cat create(@RequestBody Cat cat) throws Exception{
        return this.repository.save(cat);
    }

    @GetMapping("/{id}")
    public Cat read(@PathVariable Long id) throws Exception{
        try{
            Optional<Cat> optional = this.repository.findById(id);
            return optional.get();
        }
        catch (Exception e){
            throw new Exception("Id not found");
        }
    }

    @PatchMapping("/{id}")
    public Cat update(@PathVariable Long id, @RequestBody Cat cat) throws Exception{
        try{
            Optional<Cat> optional = this.repository.findById(id);
            if(cat.getName() != null) optional.get().setName(cat.getName());
            if(cat.getBreed() != null) optional.get().setBreed(cat.getBreed());
            if(cat.getAge() != null) optional.get().setAge(cat.getAge());
            if(cat.getAdopted() != null) optional.get().setAdopted(cat.getAdopted());
            return this.repository.save(optional.get());
        }
        catch(Exception e){
            throw new Exception("Id not found");
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) throws Exception{
        try{
            Optional<Cat> optional = this.repository.findById(id);
            this.repository.delete(optional.get());
            return "Deleted";
        }
        catch(Exception e){
            throw new Exception("Id not found");
        }
    }

    @GetMapping()
    public Iterable<Cat> list() throws Exception{
        return this.repository.findAll();
    }

}
