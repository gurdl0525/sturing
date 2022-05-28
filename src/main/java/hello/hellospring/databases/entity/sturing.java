package hello.hellospring.databases.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sturing")
@Entity
public class sturing {
    @Id
    private Integer id;
    private String name;
}
