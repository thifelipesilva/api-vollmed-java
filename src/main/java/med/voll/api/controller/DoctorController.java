package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import med.voll.api.doctor.DataDoctorCreate;
import med.voll.api.doctor.Doctor;
import med.voll.api.doctor.DoctorRepository;
import med.voll.api.doctor.DataDoctorList;
import med.voll.api.doctor.DataDoctorUpdate;

@RestController
@RequestMapping("doctor")
public class DoctorController {

    
    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public void create(@RequestBody @Valid DataDoctorCreate data) {
        repository.save(new Doctor(data));
    }
    
    @GetMapping
    public Page<DataDoctorList> getListDoctors(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        return repository.findAllByActiveTrue(pageable).map(DataDoctorList::new);
    }

    @PutMapping
    @Transactional
    public void updateDoctor(@RequestBody @Valid DataDoctorUpdate data) {
        var doctor = repository.getReferenceById(data.id());
        doctor.updateData(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteDoctor(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);
        doctor.delete();
    }
}
