package lt.viko.eif.tpetrauskas.kindergarten.response;

import lombok.Getter;
import lt.viko.eif.tpetrauskas.kindergarten.model.Group;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GroupResponse {
    private final Long id;
    private final String name;
    private final List<ChildResponse> children;

    public GroupResponse(Group group) {
        this.id = group.getId();
        this.name = group.getName();
        this.children = group.getChildren()
                .stream()
                .map(ChildResponse::new)
                .collect(Collectors.toList());
    }
}
