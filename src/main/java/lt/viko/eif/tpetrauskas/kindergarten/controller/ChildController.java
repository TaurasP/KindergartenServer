package lt.viko.eif.tpetrauskas.kindergarten.controller;

import lt.viko.eif.tpetrauskas.kindergarten.response.ChildResponse;
import lt.viko.eif.tpetrauskas.kindergarten.model.Child;
import lt.viko.eif.tpetrauskas.kindergarten.service.ChildService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/children")
public class ChildController {

    private final ChildService childService;

    public ChildController(ChildService childService) {
        this.childService = childService;
    }

    @GetMapping("/{id}")
    public ChildResponse getChildById(@PathVariable Long id) {
        return childService.getChildById(id);
    }

    @GetMapping
    public List<ChildResponse> getAllChildren() {
        return childService.getAllChildren();
    }

    @PostMapping
    public ResponseEntity<String> createChild(@RequestBody Child child) {
        return childService.createChild(child);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateChild(@PathVariable Long id, @RequestBody Child child) {
        return childService.updateChild(id, child);
    }

    @DeleteMapping("/{id}")
    public void deleteChild(@PathVariable Long id) {
        childService.deleteChild(id);
    }
}

