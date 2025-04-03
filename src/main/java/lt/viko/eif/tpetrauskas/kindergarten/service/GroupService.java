package lt.viko.eif.tpetrauskas.kindergarten.service;

import lt.viko.eif.tpetrauskas.kindergarten.model.Group;
import lt.viko.eif.tpetrauskas.kindergarten.repository.GroupRepository;
import lt.viko.eif.tpetrauskas.kindergarten.response.GroupResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupService {
    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<GroupResponse> getAllGroups() {
        return mapGroupsToGroupResponses(groupRepository.findAll());
    }

    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }

    public Group updateGroup(Long id, Group updatedGroup) {
        return groupRepository.findById(id)
                .map(group -> {
                    group.setName(updatedGroup.getName());
                    return groupRepository.save(group);
                })
                .orElseThrow(() -> new RuntimeException("Group not found"));
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
