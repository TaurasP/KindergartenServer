package lt.viko.eif.tpetrauskas.kindergarten.service;

import lt.viko.eif.tpetrauskas.kindergarten.model.Group;
import lt.viko.eif.tpetrauskas.kindergarten.repository.ChildRepository;
import lt.viko.eif.tpetrauskas.kindergarten.repository.GroupRepository;
import lt.viko.eif.tpetrauskas.kindergarten.response.GroupResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final ChildRepository childRepository;

    public GroupService(GroupRepository groupRepository, ChildRepository childRepository) {
        this.groupRepository = groupRepository;
        this.childRepository = childRepository;
    }

    public List<GroupResponse> getAllGroups() {
        return mapGroupsToGroupResponses(groupRepository.findAll());
    }

    public ResponseEntity<String> createGroup(Group group) {
        Group createdGroup = groupRepository.save(group);
        createdGroup.getChildren().forEach(child -> child.setGroup(createdGroup));
        childRepository.saveAll(createdGroup.getChildren());
        return new ResponseEntity<>("Group created", HttpStatus.OK);
    }

    public ResponseEntity<String> updateGroup(Long id, Group updatedGroup) {
        updatedGroup.getChildren().forEach(child -> child.setGroup(updatedGroup));
        childRepository.saveAll(updatedGroup.getChildren());
        groupRepository.findById(id)
                .map(group -> {
                    group.setName(updatedGroup.getName());
                    return groupRepository.save(group);
                })
                .orElseThrow(() -> new RuntimeException("Group not found"));
        return new ResponseEntity<>("Group updated", HttpStatus.OK);
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }

    public List<GroupResponse> mapGroupsToGroupResponses(List<Group> groups) {
        return groups.stream()
                .map(GroupResponse::new)
                .collect(Collectors.toList());
    }

}
