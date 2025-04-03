package lt.viko.eif.tpetrauskas.kindergarten.response;

import lombok.Getter;
import lt.viko.eif.tpetrauskas.kindergarten.model.Child;

import java.util.Date;

@Getter
public class ChildResponse {
    private final Long id;
    private final String name;
    private final String surname;
    private final Date dateOfBirth;
    private final Long groupId;

    public ChildResponse(Child child) {
        this.id = child.getId();
        this.name = child.getName();
        this.surname = child.getSurname();
        this.dateOfBirth = child.getDateOfBirth();
        this.groupId = child.getGroup() != null ? child.getGroup().getId() : null;
    }

}
