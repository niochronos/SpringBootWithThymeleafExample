package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@SqlResultSetMappings({
    @SqlResultSetMapping(name = "SqlResultSetMapping.count", columns = @ColumnResult(name = "cnt"))
})
@NamedQueries({
    @NamedQuery(name = "ContactEntity.findOpenMsgs",
        query = "SELECT c FROM ContactEntity c WHERE c.status = :status"),
    @NamedQuery(name = "ContactEntity.updateMsgStatus",
        query = "UPDATE ContactEntity c SET c.status = ?1 WHERE c.contactId = ?2")
})
@NamedNativeQueries({
    @NamedNativeQuery(name = "ContactEntity.findOpenMsgsNative",
        query = "SELECT * FROM contact_msg c WHERE c.status = :status"
        ,resultClass = ContactEntity.class),
    @NamedNativeQuery(name = "ContactEntity.findOpenMsgsNative.count",
        query = "select count(*) as cnt from contact_msg c where c.status = :status",
        resultSetMapping = "SqlResultSetMapping.count"),
        /*Spring Data JPA doesn’t support dynamic sorting for native queries.
        Doing that would require Spring Data to analyze the provided statement and generate
        the ORDER BY clause in the database-specific dialect. This would be a very complex operation
        and is currently not supported by Spring Data JPA.*/
    @NamedNativeQuery(name = "ContactEntity.updateMsgStatusNative",
        query = "UPDATE contact_msg c SET c.status = ?1 WHERE c.contact_id = ?2")
})
@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "contact_msg")
public class ContactEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
//    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "contact_id")
    private int contactId;

    /*
    * @NotNull: Checks if a given field is not null but allows empty values & zero elements inside collections.
      @NotEmpty: Checks if a given field is not null and its size/length is greater than zero.
      @NotBlank: Checks if a given field is not null and trimmed length is greater than zero.
    * */
    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @NotBlank(message = "Mobile number must not be blank")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNum;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Subject must not be blank")
    @Size(min = 5, message = "Subject must be at least 5 characters long")
    private String subject;

    @NotBlank(message = "Message must not be blank")
    @Size(min = 10, message = "Message must be at least 10 characters long")
    private String message;

    private String status;
}
