package lt.viko.eif.tpetrauskas.kindergarten.service;

import lt.viko.eif.tpetrauskas.kindergarten.response.ChildResponse;
import lt.viko.eif.tpetrauskas.kindergarten.model.Child;
import lt.viko.eif.tpetrauskas.kindergarten.repository.ChildRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChildService {

    private final ChildRepository childRepository;

    public ChildService(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    public ChildResponse getChildById(Long id) {
        return childRepository.findById(id)
                .map(ChildResponse::new)
                .orElseThrow(() -> new RuntimeException("Child not found"));
    }


    public List<ChildResponse> getAllChildren() {
        return mapToChildResponse(childRepository.findAll());
    }

    public ResponseEntity<String> createChild(Child child) {
        childRepository.save(child);
        return new ResponseEntity<>("Child created", HttpStatus.OK);
    }

    public ResponseEntity<String> updateChild(Long id, Child updatedChild) {
        childRepository.findById(id)
            .map(child -> {
                child.setName(updatedChild.getName());
                child.setSurname(updatedChild.getSurname());
                child.setDateOfBirth(updatedChild.getDateOfBirth());
                child.setGroup(updatedChild.getGroup());
                return childRepository.save(child);
            })
            .orElseThrow(() -> new RuntimeException("Child not found"));
        return new ResponseEntity<>("Child updated", HttpStatus.OK);
    }

    public void deleteChild(Long id) {
        childRepository.deleteById(id);
    }

    private List<ChildResponse> mapToChildResponse(List<Child> children) {
        return children.stream()
                .map(ChildResponse::new)
                .collect(Collectors.toList());
    }
}
