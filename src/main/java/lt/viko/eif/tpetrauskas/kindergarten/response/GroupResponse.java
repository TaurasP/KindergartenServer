package lt.viko.eif.tpetrauskas.kindergarten.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.viko.eif.tpetrauskas.kindergarten.model.Group;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class GroupResponse {
    private Long id;
    private String name;
    private List<ChildResponse> children;

    public GroupResponse(Group group) {
        this.id = group.getId();
        this.name = group.getName();
        this.children = group.getChildren()
                .stream()
                .map(ChildResponse::new)
                .collect(Collectors.toList());
    }
}
