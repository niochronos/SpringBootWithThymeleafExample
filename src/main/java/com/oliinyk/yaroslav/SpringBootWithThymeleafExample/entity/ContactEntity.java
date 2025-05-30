package com.oliinyk.yaroslav.SpringBootWithThymeleafExample.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//@SqlResultSetMappings({
//    @SqlResultSetMapping(name = "SqlResultSetMapping.count", columns = @ColumnResult(name = "cnt"))
//})
//@NamedQueries({
//    @NamedQuery(name = "ContactEntity.findOpenMsgs",
//        query = "SELECT c FROM ContactEntity c WHERE c.status = :status"),
//    @NamedQuery(name = "ContactEntity.updateMsgStatus",
//        query = "UPDATE ContactEntity c SET c.status = ?1 WHERE c.contactId = ?2")
//})
//@NamedNativeQueries({
//    @NamedNativeQuery(name = "ContactEntity.findOpenMsgsNative",
//        query = "SELECT * FROM contact_msg c WHERE c.status = :status"
//        ,resultClass = ContactEntity.class),
//    @NamedNativeQuery(name = "ContactEntity.findOpenMsgsNative.count",
//        query = "select count(*) as cnt from contact_msg c where c.status = :status",
//        resultSetMapping = "SqlResultSetMapping.count"),
//        /*Spring Data JPA doesn’t support dynamic sorting for native queries.
//        Doing that would require Spring Data to analyze the provided statement and generate
//        the ORDER BY clause in the database-specific dialect. This would be a very complex operation
//        and is currently not supported by Spring Data JPA.*/
//    @NamedNativeQuery(name = "ContactEntity.updateMsgStatusNative",
//        query = "UPDATE contact_msg c SET c.status = ?1 WHERE c.contact_id = ?2")
//})
@Getter
@Setter
@Entity
@Table(name = "contact_msg")
public class ContactEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
//    @GenericGenerator(name = "native", strategy = "native")
//    @Column(name = "contact_id")
    private int contactId;

    private String name;

    private String mobileNum;

    private String email;

    private String subject;

    private String message;

    private String status;
}
