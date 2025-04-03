package lt.viko.eif.tpetrauskas.kindergarten.service;

import lt.viko.eif.tpetrauskas.kindergarten.response.ChildResponse;
import lt.viko.eif.tpetrauskas.kindergarten.model.Child;
import lt.viko.eif.tpetrauskas.kindergarten.repository.ChildRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChildService {
    private final ChildRepository childRepository;

    public ChildService(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    public List<ChildResponse> getAllChildren() {
        return mapToChildResponse(childRepository.findAll());
    }

    public Child createChild(Child child) {
        return childRepository.save(child);
    }

    public Child updateChild(Long id, Child updatedChild) {
        return childRepository.findById(id)
                .map(child -> {
                    child.setName(updatedChild.getName());
                    child.setSurname(updatedChild.getSurname());
                    child.setDateOfBirth(updatedChild.getDateOfBirth());
                    child.setGroup(updatedChild.getGroup());
                    return childRepository.save(child);
                })
                .orElseThrow(() -> new RuntimeException("Child not found"));
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
