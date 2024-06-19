package com.ekojean.patikaclone.Controller;

import com.ekojean.patikaclone.Data.PatikaMySqlDao;
import com.ekojean.patikaclone.Entities.Patika;
import com.ekojean.patikaclone.Interfaces.IController;
import com.ekojean.patikaclone.Interfaces.IServices;
import com.ekojean.patikaclone.Services.PatikaServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/patika")
public class PatikaController implements IController<Patika> {

    private final IServices<Patika> patikaServices;

    public PatikaController() {
        this.patikaServices = new PatikaServices(new PatikaMySqlDao());
    }

    @GetMapping
    @Override
    public List<Patika> getList(String findText) {
        return patikaServices.getList(findText);
    }

    @PostMapping
    @Override
    public boolean add(Patika patika) {
        return patikaServices.add(patika);
    }

    @PutMapping
    @Override
    public boolean update(Patika patika) {
        return patikaServices.update(patika);
    }

    @DeleteMapping
    @Override
    public boolean delete(Patika patika) {
        return patikaServices.delete(patika);
    }
}
