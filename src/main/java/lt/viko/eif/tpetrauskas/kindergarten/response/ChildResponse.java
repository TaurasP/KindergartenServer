package lt.viko.eif.tpetrauskas.kindergarten.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.viko.eif.tpetrauskas.kindergarten.model.Child;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ChildResponse {
    private Long id;
    private String name;
    private String surname;
    private Date dateOfBirth;
    private Long groupId;

    public ChildResponse(Child child) {
        this.id = child.getId();
        this.name = child.getName();
        this.surname = child.getSurname();
        this.dateOfBirth = child.getDateOfBirth();
        this.groupId = child.getGroup() != null ? child.getGroup().getId() : null;
    }

}
